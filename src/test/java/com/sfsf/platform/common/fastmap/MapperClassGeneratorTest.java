package com.sfsf.platform.common.fastmap;


import java.beans.IntrospectionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class MapperClassGeneratorTest {

    private static Class<UserMapper> stm;

    public static void setUpBeforeClass() throws Exception {
        MapperClassGenerator generator = new MapperClassGenerator();
        stm = generator.generate(UserMapper.class);
    }

    public static void tearDownAfterClass() throws Exception {}

    @Test
    public void testCreateClass() throws InstantiationException, IllegalAccessException, IntrospectionException {
        UserBO source = new UserBO();
        source.setId("1");
        source.setName("name");

        UserMapper st = (UserMapper)stm.newInstance();
        UserDTO dto = st.mapBOToDTO(source);
        assertEquals(dto.getUserName(), source.getName());

    }

    @Test
    public void testMultiFields() throws InstantiationException, IllegalAccessException, IntrospectionException {
        UserBO source = new UserBO();
        source.setId("1");
        source.setName("name");
        OrgBO org = new OrgBO();
        org.setName("SFSF");
        source.setOrg(org);
        UserMapper st = (UserMapper)stm.newInstance();
        UserDTO dto = st.mapBOToDTO(source);
        assertEquals(dto.getOrgName(), source.getOrg().getName());
    }

}
