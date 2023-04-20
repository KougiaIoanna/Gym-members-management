package com.example.gym_management;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.gym_management.controller.MemberController;
import com.example.gym_management.model.Member;
import com.example.gym_management.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MemberControllerTest {

  @InjectMocks
  private MemberController memberController;

  @Mock
  private MemberService memberService;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
  }

  @Test
  public void testAddMember() throws Exception {
    Member member = new Member(
      1,
      "John Doe",
      "john@doe.com",
      "6942563665",
      "Mpoumpoulinas 25, Ioannina",
      3,
      "2000-10-01",
      "2023-02-03",
      "active"
    );
    when(memberService.addMember(any(Member.class))).thenReturn(member);

    mockMvc
      .perform(
        post("/member")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(member))
      )
      .andExpect(status().isCreated())
      .andExpect(content().json(objectMapper.writeValueAsString(member)));

    verify(memberService).addMember(any(Member.class));
  }

  @Test
  public void testGetMembers() throws Exception {
    // Prepare test data
    List<Member> members = new ArrayList<>();
    members.add(
      new Member(
        1,
        "John Doe",
        "john@doe.com",
        "6942563665",
        "Mpoumpoulinas 25, Ioannina",
        3,
        "2000-10-01",
        "2023-02-03",
        "active"
      )
    );
    members.add(
      new Member(
        2,
        "Jane Black",
        "jane@black.com",
        "6956588858",
        "Eleytherias 4, Ioannina",
        3,
        "1990-05-06",
        "2023-02-03",
        "active"
      )
    );

    // Mock the service method
    Mockito.when(memberService.getMembers()).thenReturn(members);

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/member"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].memberId", Matchers.is(1))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("John Doe"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].email",
          Matchers.is("john@doe.com")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].phone", Matchers.is("6942563665"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].address",
          Matchers.is("Mpoumpoulinas 25, Ioannina")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].membershipTypeId", Matchers.is(3))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].dateOfBirth",
          Matchers.is("2000-10-01")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[0].joinDate",
          Matchers.is("2023-02-03")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("active"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].memberId", Matchers.is(2))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Jane Black"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].email",
          Matchers.is("jane@black.com")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].phone", Matchers.is("6956588858"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].address",
          Matchers.is("Eleytherias 4, Ioannina")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].membershipTypeId", Matchers.is(3))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].dateOfBirth",
          Matchers.is("1990-05-06")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$[1].joinDate",
          Matchers.is("2023-02-03")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$[1].status", Matchers.is("active"))
      );

    // Verify the service method was called
    Mockito.verify(memberService, Mockito.times(1)).getMembers();
  }

  @Test
  public void testGetMemberById() throws Exception {
    // Prepare test data
    Member member = new Member(
      1,
      "John Doe",
      "john@doe.com",
      "6942563665",
      "Mpoumpoulinas 25, Ioannina",
      3,
      "2000-10-01",
      "2023-02-03",
      "active"
    );

    // Mock the service method
    Mockito
      .when(memberService.getMemberById(1))
      .thenReturn(Optional.of(member));

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/member/1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.memberId", Matchers.is(1)))
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John Doe"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.email", Matchers.is("john@doe.com"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.phone", Matchers.is("6942563665"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$.address",
          Matchers.is("Mpoumpoulinas 25, Ioannina")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.membershipTypeId", Matchers.is(3))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath(
          "$.dateOfBirth",
          Matchers.is("2000-10-01")
        )
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.joinDate", Matchers.is("2023-02-03"))
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.status", Matchers.is("active"))
      );

    // Verify the service method was called
    Mockito.verify(memberService, Mockito.times(1)).getMemberById(1);
  }

  @Test
  public void testUpdateMember() throws Exception {
    Member member = new Member(
      1,
      "John Doe",
      "john@doe.com",
      "6942563665",
      "Mpoumpoulinas 25, Ioannina",
      3,
      "2000-10-01",
      "2023-02-03",
      "active"
    );
    when(memberService.updateMember(any(Integer.class), any(Member.class)))
      .thenReturn(member);

    mockMvc
      .perform(
        put("/member/{id}", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(member))
      )
      .andExpect(status().isOk())
      .andExpect(content().json(objectMapper.writeValueAsString(member)));

    verify(memberService).updateMember(any(Integer.class), any(Member.class));
  }

  @Test
  public void testGetMemberById_NotFound() throws Exception {
    // Mock the service method
    Mockito.when(memberService.getMemberById(1)).thenReturn(Optional.empty());

    // Perform GET request
    mockMvc
      .perform(MockMvcRequestBuilders.get("/member/1"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());

    // Verify the service method was called
    Mockito.verify(memberService, Mockito.times(1)).getMemberById(1);
  }

  @Test
  public void testDeleteMember() throws Exception {
    Member member = new Member(
      1,
      "John Doe",
      "john@doe.com",
      "6942563665",
      "Mpoumpoulinas 25, Ioannina",
      3,
      "2000-10-01",
      "2023-02-03",
      "active"
    );
    when(memberService.deleteMember(1)).thenReturn(true);

    mockMvc
      .perform(delete("/member/1", 1).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    verify(memberService).deleteMember(1);
  }

  @Test
  public void testUpdateStatus() throws Exception {
    Member member = new Member(
      1,
      "John Doe",
      "john@doe.com",
      "6942563665",
      "Mpoumpoulinas 25, Ioannina",
      3,
      "2000-10-01",
      "2023-02-03",
      "active"
    );
    when(memberService.updateStatus(any(Integer.class), any(String.class)))
      .thenReturn(member);

    mockMvc
      .perform(
        patch("/member/{memberId}/status", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(("innactive"))
      )
      .andExpect(status().isOk())
      .andExpect(content().json(objectMapper.writeValueAsString(member)));

    verify(memberService).updateStatus(1, "innactive");
  }
}
