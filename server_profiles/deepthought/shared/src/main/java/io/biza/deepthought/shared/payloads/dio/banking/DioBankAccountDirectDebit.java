/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.deepthought.shared.payloads.dio.banking;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.deepthought.shared.payloads.dio.enumerations.DioSchemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "A Deep Thought Bank Account Container")
public class DioBankAccountDirectDebit {

  @JsonProperty("id")
  @NotNull
  @NonNull
  @Schema(description = "Deep Thought Bank Account Identifier",
      defaultValue = "00000000-0000-0000-0000-000000000000")
  @Builder.Default
  public UUID id = new UUID(0, 0);

  @JsonProperty("schemeType")
  @NotNull
  @NonNull
  @Schema(description = "Deep Thought Scheme Type", defaultValue = "DIO_BANKING")
  @Builder.Default
  public DioSchemeType schemeType = DioSchemeType.DIO_BANKING;

  @Schema(required = true)
  @NotNull
  @JsonProperty("authorisedEntity")
  DioBankAuthorisedEntity authorisedEntity;

  @Schema(description = "The date and time of the last debit executed under this authorisation",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastDebitDateTime")
  OffsetDateTime lastDebitDateTime;

  @Schema(description = "The amount of the last debit executed under this authorisation")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("lastDebitAmount")
  BigDecimal lastDebitAmount;

}
