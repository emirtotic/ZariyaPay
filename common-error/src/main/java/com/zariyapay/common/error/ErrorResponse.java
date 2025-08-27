package com.zariyapay.common.error;

import java.time.Instant;

public record ErrorResponse(
        String code,
        String message,
        Instant timestamp,
        String path,
        String traceId
) {}
