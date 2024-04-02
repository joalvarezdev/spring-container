package com.joalvarez.springcontainer.data.dto.generals;

import java.util.List;

public record HttpDTO (int code, String message, List<String> details, String nested) implements BaseDTO {}