package com.joalvarez.springcontainer.data.dto.generals;

import com.joalvarez.springcontainer.constants.IResponse;

import java.util.List;

public record ResponseDTO (int code, String message, List<String> details) implements BaseDTO {}