package com.symplora.leavemanagement.exception;

import java.time.LocalDateTime;

public record ApiError(String message, int status, LocalDateTime timestamp) {
}
