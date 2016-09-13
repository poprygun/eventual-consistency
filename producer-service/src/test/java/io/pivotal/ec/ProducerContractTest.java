package io.pivotal.ec;


import org.junit.Before;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.mockito.Mock;
import org.mockito.Mockito;


public class ProducerContractTest {

    private MessageGateway messageGateway = Mockito.mock(MessageGateway.class);

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new MessagesRestController(messageGateway));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}
