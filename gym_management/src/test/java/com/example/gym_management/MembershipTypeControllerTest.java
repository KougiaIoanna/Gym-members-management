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

import com.example.gym_management.controller.MembershipTypeController;
import com.example.gym_management.model.MembershipType;
import com.example.gym_management.service.MembershipTypeService;
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
public class MembershipTypeControllerTest {

  @InjectMocks
  private MembershipTypeController membershipTypeController;

  @Mock
  private MembershipTypeService membershipTypeService;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(membershipTypeController).build();
  }

  @Test
  public void testAddMembershipType() throws Exception {
    MembershipType membershipType = new MembershipType(
      1,
      "Monthly Basic",
      "Basic monthly membership",
      new BigDecimal(50.00),
      1
    );
    when(membershipTypeService.addMembershipType(any(MembershipType.class)))
      .thenReturn(membershipType);

    mockMvc
      .perform(
        post("/membership-type")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(membershipType))
      )
      .andExpect(status().isCreated())
      .andExpect(
        content().json(objectMapper.writeValueAsString(membershipType))
      );

    verify(membershipTypeService).addMembershipType(any(MembershipType.class));
  }

  @Test
  public void testGetMembershipTypes() throws Exception {
    List<MembershipType> membershipTypes = new ArrayList<>();
    membershipTypes.add(
      new MembershipType(
        1,
        "Monthly Basic",
        "Basic monthly membership",
        new BigDecimal("50.00"),
        1
      )
    );
    membershipTypes.add(
      new MembershipType(
        2,
        "Yearly Basic",
        "Basic yearly membership",
        new BigDecimal("300.00"),
        12
      )
    );

    // Mock the service method
    when(membershipTypeService.getMembershipTypes())
      .thenReturn(membershipTypes);

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/membership-type"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].membershipTypeId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].name",
          Matchers.is("Monthly Basic")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].description",
          Matchers.is("Basic monthly membership")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].cost",
          Matchers.comparesEqualTo(50.00)
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].numberOfMonths", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].membershipTypeId", Matchers.is(2))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Yearly Basic"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].description",
          Matchers.is("Basic yearly membership")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].cost",
          Matchers.comparesEqualTo(300.00)
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].numberOfMonths", Matchers.is(12))
      );

    // Verify the service method was called
    Mockito
      .verify(membershipTypeService, Mockito.times(1))
      .getMembershipTypes();
  }

  @Test
  public void testGetMembershipTypeById() throws Exception {
    MembershipType membershipType = new MembershipType(
      1,
      "Monthly Basic",
      "Basic monthly membership",
      new BigDecimal("50.00"),
      1
    );

    // Mock the service method
    Mockito
      .when(membershipTypeService.getMembershipTypeById(1))
      .thenReturn(Optional.of(membershipType));

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/membership-type/1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.membershipTypeId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Monthly Basic"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$.description",
          Matchers.is("Basic monthly membership")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$.cost",
          Matchers.comparesEqualTo(50.00)
        )
      );

    // Verify the service method was called
    Mockito
      .verify(membershipTypeService, Mockito.times(1))
      .getMembershipTypeById(1);
  }

  @Test
  public void testUpdateMembershipType() throws Exception {
    MembershipType membershipType = new MembershipType(
      1,
      "Monthly Basic",
      "Basic monthly membership",
      new BigDecimal("50.00"),
      1
    );
    when(
      membershipTypeService.updateMembershipType(
        any(Integer.class),
        any(MembershipType.class)
      )
    )
      .thenReturn(membershipType);

    mockMvc
      .perform(
        put("/membership-type/{membershipTypeId}", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(membershipType))
      )
      .andExpect(status().isOk())
      .andExpect(
        content().json(objectMapper.writeValueAsString(membershipType))
      );

    verify(membershipTypeService)
      .updateMembershipType(any(Integer.class), any(MembershipType.class));
  }

  @Test
  public void testGetMembershipTypeById_NotFound() throws Exception {
    // Mock the service method
    when(membershipTypeService.getMembershipTypeById(1))
      .thenReturn(Optional.empty());

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/membership-type/1"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());

    // Verify the service method was called
    verify(membershipTypeService, Mockito.times(1)).getMembershipTypeById(1);
  }

  @Test
  public void testDeleteMembershipType() throws Exception {
    MembershipType membershipType = new MembershipType(
      1,
      "Monthly Basic",
      "Basic monthly membership",
      new BigDecimal(50.00),
      1
    );
    when(membershipTypeService.deleteMembershipType(1)).thenReturn(true);

    mockMvc
      .perform(
        delete("/membership-type/{membershipTypeId}", 1)
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    verify(membershipTypeService).deleteMembershipType(1);
  }
}
