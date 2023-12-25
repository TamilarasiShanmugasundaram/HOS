package com.Hos.core.request.service.impl;

import com.Hos.core.common.dto.RequestFormDTO;
import com.Hos.core.common.dto.UserRequestDTO;
import com.Hos.core.common.model.City;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.model.Response;
import com.Hos.core.common.model.User;
import com.Hos.core.common.util.Constants;
import com.Hos.core.request.repository.CityRepository;
import com.Hos.core.request.repository.RequestRepository;
import com.Hos.core.request.repository.ResponseRepository;
import com.Hos.core.request.service.RequestService;
import com.Hos.core.user.repository.UserRepository;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserService userService;

    @Autowired
	private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<Request> getRequest() {

        return requestRepository.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findByIsDeletedFalse();
    }

    @Override
	public Request createRequest(RequestFormDTO requestFormDTO) {
		Request request = new ModelMapper().map(requestFormDTO, Request.class);
        User user = userService.getUserById(request.getCreatedBy());
        request.setPhoneNumber(user.getPhoneNumber());
		request.setCities(getCitiesById(requestFormDTO.getCityIds()));
		return requestRepository.save(request);
	}

    @Override
    public City getCityById(long id) {
        return cityRepository.getCityById(id);
    }

    @Override
	public List<City> getCitiesById(List<Long> cityIds) {
		return cityRepository.getAllCityById(cityIds);
	}

    @Override
    public Request getRequestById(long id) {
        return requestRepository.findByIdAndIsDeletedFalseAndIsRequestClosedFalse(id);
    }

    @Override
	public List<Request> getUserRequestList(UserRequestDTO userRequestDTO) throws Exception {
		UserRequestDTO userMyRequest = modelMapper.map(userRequestDTO, UserRequestDTO.class);
		try {
			if (!Objects.isNull(userMyRequest.getCreatedBy()) &&
					userRepository.findById(userMyRequest.getCreatedBy()).isPresent()) {

				if (userMyRequest.getIsOtherRequest() != null && userMyRequest.getIsOtherRequest()) {
                    return  requestRepository.getRequestsExcludingCreatedBy(userMyRequest.getType(),
                    userMyRequest.getCityIds(), userMyRequest.getCreatedBy());
                } else if (userMyRequest.getIsMyRequest() != null && userMyRequest.getIsMyRequest()) {
                    return requestRepository.getRequestsIncludingCreatedBy(userMyRequest.getType(),
                            userMyRequest.getCityIds(), userMyRequest.getCreatedBy());
                }
                return requestRepository.findByIsRequestClosedFalseOrderByIdDesc();

			} else if (!Objects.isNull(userMyRequest.getCityIds()) ||
					!Objects.isNull(userMyRequest.getType())) {
				return requestRepository.getRequestsIncludingCreatedBy(userMyRequest.getType(),
						userMyRequest.getCityIds(), userMyRequest.getCreatedBy());
			} else {
				return requestRepository.findByIsRequestClosedFalseOrderByIdDesc();
			}
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

    @Override
    public City getCityByName(String name) {
        return cityRepository.getCityByName(name);
    }

    @Override
    public Response saveResponse(Map<String, String> request) {
        Response response = new Response();
        User responsedUser = userService.getUserById(Long.parseLong(request.get(Constants.USERID)));
        Request userRequest = getRequestById(Long.parseLong(request.get(Constants.REQUESTID)));
        System.out.println(userRequest + "  ggggggggggggggggggggggggggggg");
        User resquestedUser = userService.getUserById(userRequest.getCreatedBy());
        String notes = request.get(Constants.NOTES);
//        String body = "Hi " + resquestedUser.getFirstName() + " \nSomeone has provided a response to your request, and here are the details., \n\n" +
//                "Name: " + responsedUser.getFirstName() + " \n" +
//                "Phone number : " + responsedUser.getPhoneNumber() + " \n" +
//                "Email: " + responsedUser.getUsername() + " \n" +
//                "Notes: " + notes;
        String body = "Hi " + resquestedUser.getFirstName() + " \nYou received a response for the below request. \n\n" +
"The Request: \n" +
                "Type: " + userRequest.getType() + " \n" +
                "Category : " + userRequest.getCategory() + " \n" +
                "Notes: " + userRequest.getInfo() + " \n\n" +
                "The Response: \n" +

                "Name: " + responsedUser.getFirstName() + " \n" +
                "Phone number : " + responsedUser.getPhoneNumber() + " \n" +
                "Email: " + responsedUser.getUsername() + " \n" +
                "Notes: " + notes;

        response.setNotes(notes);
        response.setRequest(getRequestById(Long.parseLong(request.get(Constants.REQUESTID))));
        response.setUser(responsedUser);
        response.setCreatedBy(Long.parseLong(request.get(Constants.USERID)));
        response.setUpdatedBy(Long.parseLong(request.get(Constants.USERID)));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(resquestedUser.getUsername());
        simpleMailMessage.setSubject(Constants.RESPONSE_EMAIL_SUBJECT);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(Constants.EMAIL_FROM);
            javaMailSender.send(simpleMailMessage);
            System.out.println("dfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" + resquestedUser.getUsername());

        System.out.println(body);
        return responseRepository.save(response);
    }

    private void sendMail(User resquestedUser, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(resquestedUser.getUsername());
        simpleMailMessage.setSubject(Constants.RESPONSE_EMAIL_SUBJECT);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(Constants.EMAIL_FROM);
            javaMailSender.send(simpleMailMessage);

    }

    @Override
    public Request accecptResponse(Map<String, String> request) {
        Request requestValue = getRequestById(Long.parseLong(request.get(Constants.REQUESTID)));
        requestValue.setRequestClosed(true);
        return requestRepository.save(requestValue);
    }
}
