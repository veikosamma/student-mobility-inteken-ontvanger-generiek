package generiek.repository;

import generiek.model.EnrollmentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void findByIdentifier() {
        enrollmentRepository.deleteAll();
        EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
        enrollmentRequest.setOfferingId("forgery");
        enrollmentRequest.setPersonURI("https://www.test.org");
        enrollmentRequest.setScope("inteken");

        enrollmentRequest = new EnrollmentRequest(enrollmentRequest);
        assertNull(enrollmentRequest.getOfferingId());

        enrollmentRepository.save(enrollmentRequest);

        enrollmentRequest = enrollmentRepository.findByIdentifier(enrollmentRequest.getIdentifier()).get();
        assertEquals("inteken", enrollmentRequest.getScope());
    }
}