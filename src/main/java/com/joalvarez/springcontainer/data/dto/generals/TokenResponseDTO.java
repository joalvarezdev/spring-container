package com.joalvarez.springcontainer.data.dto.generals;

public record TokenResponseDTO(String token, Long expiresIn, String type) {
}
