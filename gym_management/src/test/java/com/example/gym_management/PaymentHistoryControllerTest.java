package com.example.gym_management;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.gym_management.controller.PaymentHistoryController;
import com.example.gym_management.model.PaymentHistory;
import com.example.gym_management.service.PaymentHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PaymentHistoryControllerTest {

  @InjectMocks
  private PaymentHistoryController paymentHistoryController;

  @Mock
  private PaymentHistoryService paymentHistoryService;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(paymentHistoryController).build();
  }

  @Test
  public void testAddPayment() throws Exception {
    PaymentHistory payment = new PaymentHistory(
      1,
      1,
      "2023-02-02",
      1,
      new BigDecimal("50.00")
    );
    when(paymentHistoryService.addPaymentHistory(any(PaymentHistory.class)))
      .thenReturn(payment);
    mockMvc
      .perform(
        post("/payment-history")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(payment))
      )
      .andExpect(status().isCreated())
      .andExpect(content().json(objectMapper.writeValueAsString(payment)));
    verify(paymentHistoryService).addPaymentHistory(any(PaymentHistory.class));
  }

  @Test
  public void testAllPaymentHistory() throws Exception {
    List<PaymentHistory> paymentHistory = new ArrayList<>();
    paymentHistory.add(
      new PaymentHistory(1, 1, "2023-02-02", 1, new BigDecimal("50.00"))
    );
    paymentHistory.add(
      new PaymentHistory(2, 2, "2023-03-03", 3, new BigDecimal("100.00"))
    );

    // Mock the service method
    when(paymentHistoryService.getAllPaymentHistory())
      .thenReturn(paymentHistory);

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/payment-history"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].paymentId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].memberId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].paymentDate",
          Matchers.is("2023-02-02")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].membershipTypeId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].amount",
          Matchers.comparesEqualTo(50.00)
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].paymentId", Matchers.is(2))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].memberId", Matchers.is(2))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].paymentDate",
          Matchers.is("2023-03-03")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].amount",
          Matchers.comparesEqualTo(100.00)
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].membershipTypeId", Matchers.is(3))
      );

    // Verify the service method was called
    Mockito
      .verify(paymentHistoryService, Mockito.times(1))
      .getAllPaymentHistory();
  }

  @Test
  public void testGetPaymentHistoryById() throws Exception {
    PaymentHistory payment = new PaymentHistory(
      1,
      1,
      "2023-02-02",
      1,
      new BigDecimal("50.00")
    );

    // Mock the service method
    Mockito
      .when(paymentHistoryService.getPaymentHistoryById(1))
      .thenReturn(Optional.of(payment));

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/payment-history/1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.memberId", Matchers.is(1)))
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$.paymentDate",
          Matchers.is("2023-02-02")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.membershipTypeId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$.amount",
          Matchers.comparesEqualTo(new BigDecimal("50.00"))
        )
      );

    // Verify the service method was called

    verify(paymentHistoryService, Mockito.times(1)).getPaymentHistoryById(1);
  }

  @Test
  public void testUpdatePayment() throws Exception {
    PaymentHistory payment = new PaymentHistory(
      1,
      1,
      "2023-02-02",
      1,
      new BigDecimal("50.00")
    );
    when(paymentHistoryService.updatePaymentHistory(any(PaymentHistory.class)))
      .thenReturn(payment);

    mockMvc
      .perform(
        put("/payment-history/{paymentId}", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(payment))
      )
      .andExpect(status().isOk())
      .andExpect(content().json(objectMapper.writeValueAsString(payment)));

    verify(paymentHistoryService)
      .updatePaymentHistory(any(PaymentHistory.class));
  }

  @Test
  public void testDeletePayment() throws Exception {
    PaymentHistory payment = new PaymentHistory(
      1,
      1,
      "2023-02-02",
      1,
      new BigDecimal("50.00")
    );

    when(paymentHistoryService.deletePaymentHistory(1)).thenReturn(true);

    mockMvc
      .perform(
        delete("/payment-history/{paymentId}", 1)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());

    verify(paymentHistoryService).deletePaymentHistory(1);
  }
}
