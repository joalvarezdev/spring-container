package com.joalvarez.springcontainer.utils;

import com.joalvarez.springcontainer.data.dto.generals.UserDetailsDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocalStorage {

	private static final ThreadLocal<UserDetailsDTO> USER_DETAILS = new ThreadLocal<>();

	public UserDetailsDTO getUserDetails() {
		return USER_DETAILS.get();
	}

	public void setUserDetails(UUID userId) {
		USER_DETAILS.set(new UserDetailsDTO(userId));
	}

	public void clear() {
		USER_DETAILS.remove();
	}
}
