package com.ibm.geolocation.service;

import com.ibm.geolocation.models.IpInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class IPServiceTest {

    @Test
    void getIpInformation_WithCorrectIP() {
        String correctIP = "24.48.9.1";
        IPService service = new IPService();
        IpInfo info = service.getIpInformation(correctIP);
        assertEquals(info.getCountry(),"Canada");
        assertEquals(info.getCity(),"Blainville");
    }

    @Test
    void getIpInformation_WithWrongIP() {
        String correctIP = "24.48...1";
        IPService service = new IPService();
        IpInfo info = service.getIpInformation(correctIP);
        assertNull(info.getCountry());
        assertNull(info.getCity());
    }
}