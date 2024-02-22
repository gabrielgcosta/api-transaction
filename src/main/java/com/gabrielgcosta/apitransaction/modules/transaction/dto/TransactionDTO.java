package com.gabrielgcosta.apitransaction.modules.transaction.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(BigDecimal value, UUID senderId, UUID receiverId) {

}
