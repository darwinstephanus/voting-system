package com.darwin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.darwin.dao.AdminDAO;
import com.darwin.dao.UserDAO;
import com.darwin.dao.VoterDAO;
import com.darwin.dto.AdminDTO;
import com.darwin.model.Admin;
import com.darwin.model.User;
import com.darwin.model.Voter;
import com.darwin.service.AdminService;
import com.darwin.service.JwtUserDetailsService;
import com.darwin.service.VoterService;

//@JsonTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class VotingSystemApplicationTests {

//	@Autowired 
//	private JacksonTester <AdminDTO> json;
//	private static final String adminFirstName = "First";
//	private static final String adminLastName = "Last";
//	private static final String email = "firstlast@gmail.com";
//	private static final String username = "usernameAdmin";
//	private static final String password = "passwordAdmin";
//    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(("dd/MM/yyyy"));
//    private static final String FIRST_NAME = "First name";
//    private static final String SECOND_NAME = "Second name";
//    private static final String DATE_OF_BIRTH_STRING = "01/12/2020";
//    private static final Date DATE_OF_BIRTH = parseDate(DATE_OF_BIRTH_STRING);
//    private static final String PROFESSION = "Professional time waster";
//    private static final BigDecimal SALARY = BigDecimal.ZERO;
	
//    private static final String JSON_TO_DESERIALIZE =
//        "{\"adminFirstName\":\"" +
//        adminFirstName +
//        "\",\"adminLastName\":\"" +
//        adminLastName +
//        "\",\"email\":\"" +
//        email +
//        "\",\"username\":\"" +
//        username +
//        "\",\"password\":" +
//        password +
//        "}";
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private VoterService voterService;
	
	@MockBean
	private UserDAO userDAO;
	
	@MockBean
	private AdminDAO adminDAO;
	
	@MockBean
	private VoterDAO voterDAO;
	
//	AdminDTO adminDTO2;
	
//	@Before
//    public void setup() throws ParseException {
//		adminDTO2 = new AdminDTO(adminFirstName, adminLastName, email, username, password);
//    }
//    @Test
//    public void firstNameSerializes() throws IOException {
//        assertThat(this.json.write(adminDTO2))
//            .extractingJsonPathStringValue("@.adminFirstName")
//            .isEqualTo(adminFirstName);
//    }
//    @Test
//    public void secondNameSerializes() throws IOException {
//        assertThat(this.json.write(adminDTO2))
//            .extractingJsonPathStringValue("@.adminLastName")
//            .isEqualTo(adminLastName);
//    }
//    @Test
//    public void dateOfBirthSerializes() throws IOException, ParseException {
//        assertThat(this.json.write(adminDTO2))
//            .extractingJsonPathStringValue("@.email")
//            .isEqualTo(email);
//    }
//    @Test
//    public void professionSerializes() throws IOException {
//        assertThat(this.json.write(adminDTO2))
//            .extractingJsonPathStringValue("@.username")
//            .isEqualTo(username);
//    }
//    @Test
//    public void salarySerializes() throws IOException {
//        assertThat(this.json.write(adminDTO2))
//            .extractingJsonPathStringValue("@.password")
//            .isEqualTo(password);
//    }
//    @Test
//    public void firstNameDeserializes() throws IOException {
//        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getAdminFirstName()).isEqualTo(adminFirstName);
//    }
//    @Test
//    public void secondNameDeserializes() throws IOException {
//        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getAdminLastName()).isEqualTo(adminLastName);
//    }
//    @Test
//    public void dateOfBirthDeserializes() throws IOException {
//        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getEmail())
//            .isEqualTo(email);
//    }
//    @Test
//    public void professionDeserializes() throws IOException {
//        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getUsername()).isEqualTo(username);
//    }
//    @Test
//    public void salaryDeserializes() throws IOException {
//        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getPassword()).isEqualTo(password);
//    }
//}
	
	@Test
	public void getAdminsTest() {
		when(adminDAO.findAll()).thenReturn(Stream
				.of(new Admin("Honglin", "China", "Honglin@gmail.com", "HongUsername"),
						new Admin("Shekinah", "Congo", "Shekinah@gmail.com", "ShekUsername")). collect(Collectors.toList()));
		assertEquals(2, adminService.getAllAdmin().size());
	}
	
	@Test
	public void getVotersByCity() {
		String city = "bryan";
		when(voterDAO.findAllByCity(city))
				.thenReturn(Stream.of(new Voter("Honglin", "China", "Honglin@gmail.com", "HongUsername", "9999", "mainst", "bryan", "texas")).collect(Collectors.toList()));
		assertEquals(1, voterService.getVoterByCity(city).size());
	}
	
//	
//	@Test
//	public void getUserbyAddressTest() {
//		String address = "Pittsburgh";
//		when(repository.findByAddress(address))
//				.thenReturn(Stream.of(new User(new BigInteger("376"), "Brijesh", 31, "Canada")).collect(Collectors.toList()));
//		assertEquals(1, service.getUserByAddress(address).size());
//	}
	
	
//	@Test
//	public void saveAdminTest() throws Exception {
//		Admin admin = new Admin();
//		admin.setAdminFirstName("Honglin");
//		admin.setAdminLastName("China");
//		admin.setEmail("Honglin@gmail.com");
//		admin.setUsername("HongUsername");
//		AdminDTO adminDTO = new AdminDTO();
//		adminDTO.setAdminFirstName("Honglin");
//		adminDTO.setAdminLastName("China");
//		adminDTO.setEmail("Honglin@gmail.com");
//		adminDTO.setUsername("HongUsername");
//		when(adminDAO.save(admin)).thenReturn(admin);
//		assertEquals(admin, adminService.createAdmin(adminDTO));
//	}
	
//	@Test
//	public void saveUserTest() {
//		User user = new User(new BigInteger("999"), "Neil", 33, "Gujarat");
//		when(repository.save(user)).thenReturn(user);
//		assertEquals(user, service.addUser(user));
//	}
//	@Test
//	public void deleteUserTest() {
//		User user = new User(new BigInteger("999"), "Timothy", 33, "Wisconsin");
//		service.deleteUser(user);
//		verify(repository, times(1)).delete(user);
//	}
	
//	@Test
//	public void deleteAdminTest() throws Exception {
//		AdminDTO adminDTO = new AdminDTO();
//		adminDTO.setAdminFirstName("Honglin");
//		adminDTO.setAdminLastName("China");
//		adminDTO.setEmail("Honglin@gmail.com");
//		adminDTO.setUsername("HongUsername");
//		adminService.createAdmin(adminDTO);
//		Admin admin = new Admin("Honglin", "China", "Honglin@gmail.com", "HongUsername");
//		adminService.deleteAdmin(1);
//		verify(adminDAO, times(1)).delete(admin);
//	}
}
