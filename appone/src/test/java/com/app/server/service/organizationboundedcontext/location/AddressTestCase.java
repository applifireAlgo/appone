package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private Address createAddress(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        State state = new State();
        state.setStateCodeChar3("Fma34d7clFKAoLVNIITXpiDb7P6s0yIv");
        state.setStateCodeChar2("GQ46Gy4gNiXQO7XMVi2yCUZ53q36VGmm");
        state.setStateCapitalLongitude(1);
        state.setStateFlag("AyeNWaMwQE884Ys8vZLL8dJyo8jZPsDYXG9HvJWESjDB6vyYgq");
        state.setStateCode(1);
        state.setStateCapital("1GFq6c0a2L9uh6gxd3sYS9rKReNfqYZMDeqpewF9gdG5DchFRA");
        Country country = new Country();
        country.setCountryCode1("P7P");
        country.setCurrencySymbol("tBIqBgsK37AnRK9Uh4Cs4TKFf0mmfnnl");
        country.setCapitalLatitude(4);
        country.setCountryCode2("fvX");
        country.setCurrencyName("eIxU0RUjdEegtYcsU3dEZqLsmJtzliUKora7cM9tit3AadHuUC");
        country.setCapitalLongitude(1);
        country.setIsoNumeric(908);
        country.setCountryFlag("kIVfHY4GRY77HnQCP0vVTS6abqgohye7cn07acKimRV3XgCqYA");
        country.setCountryName("7OdkpKQHAjbMGQOBY0bi30AsCKPPtGVRcZuosT2YP6xRt3rlib");
        country.setCurrencyCode("8zo");
        country.setCapital("kdmjbuStAYS5H8Ow7iXoebX7jVaHj1xO");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("m3kbwzPAblMEc9fmzWxWRuzw7uEiVHl6");
        state.setStateCodeChar2("6P8wVVnjPXyk2SB6mJOAR7lG3FWvqehJ");
        state.setStateCapitalLongitude(5);
        state.setStateFlag("VWxOzBuZpsCsHO63q8KL7FVI35bdNgZBkCYFCtvn0ca7YQpwRj");
        state.setStateCode(2);
        state.setStateCapital("f7uZkvc1CKpqhZwKXQ4wKupVRW7HlYhqrQW0xZNRPPuglFpyDY");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("pM2aZNIiXRtRjK4r1Mvz52OvjQpnqnioNv4qq4JxCJd6ayAhZn");
        state.setStateDescription("hwNDOB6kTKhoBVqz5CnkP0wkkdBN2fRJ6h15BjJSkzXqjL3jrQ");
        state.setStateCapitalLatitude(4);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("a5XfpMVQ0cGQPmIvB4cPrlMorx55fAB4XbCF5iu5FAWo08tOdb");
        addresstype.setAddressTypeDesc("0O3OzHZqBkRnHWoMCGT4eUA9DiIho5tj3rbz9wH8CK9BMBXq1l");
        addresstype.setAddressType("WH9PIsT96K3yZYgUzDaXJyK7KYXfkFz9tCLX9Thr1pXBvUyaQH");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("Flp3K73LwrBILwVJ7qDyUK6i7fDGACGpG2tI5WbvdJd8EnFPHi");
        city.setCityCode(2);
        city.setCityDescription("SCGoQHUm1oki26jljcl6kDLN5LupMs9a4HXxQWnaMlwOYqTmvp");
        city.setCityCodeChar2("BjNkKaL1vNRwdCN6w1xx5wjkVElgPbHA");
        city.setCityLongitude(8);
        city.setCityLatitude(2);
        city.setCityName("Sc7iygIlHvPc7q30cKOhCO4daIsUBQLqS25koJlrYYn8flaXhI");
        city.setCityCode(2);
        city.setCityDescription("KAWgWZ958l5FJeLyZGrsKTOV2FBR8e0zF2lL9eeudESnHDiFve");
        city.setCityCodeChar2("YL8sWMDEkeGeotq9jXQSgQDbC9OYsfGK");
        city.setCityLongitude(4);
        city.setCityLatitude(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("K8xgNCkNzyLstSJHmOjaM9vG1j4Bxan9DvbIDSQObu7vbqV14L");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("Dno1hRovuRnrqndFKF9YTeXRRB4cIh3beATqpY8KKbjImvOhzO");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("GXPZbCpdHAR");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setAddress1("fdd8wMWb4R2GN9JnrAiodmxq7hHCah8kCL3oveCDmXr51LgeNL");
        address.setZipcode("IXcrkY");
        address.setAddress3("35YZr5y9iHubhoHXNe28noUmmqdKBruFf78tJ9wJ9SH2iTBgvn");
        address.setLongitude("P09FmCmJ53HmLReBPrj57oXbpcMvNaf04h8LsCjXRL54yK2der");
        address.setLatitude("3pqsyZgyMFJcAmloronmjAH5ARwzQ3qmH0XM2zSq9B32WEQvnH");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress2("nDeEkaZ1bs8M6n0gUALLlZ2gqMbNZ7QqYXN71ihz3Tmc07PxFo");
            address.setAddressLabel("pSz8NnXZ7Ak");
            address.setAddress1("rVNnA69EmhV7zyGOoHxvK4kEnfaqJ6cQs4XULyb0JYxLIU9l7T");
            address.setZipcode("IxKHZf");
            address.setAddress3("PhHHqkXZZpe6uTRDd9q54MUUZl3lZIB52WBL61qO8z5BjHQvLT");
            address.setLongitude("WYoa0k6rC5SaOkZII2RYqMR6b9S7z4I9mHGlFht0X49pIgbPQn");
            address.setLatitude("K4R5d4M3CjLMADYK0DAvhNA2gWcrvurb8ZQBRocKz2UFok5ttF");
            address.setVersionId(1);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "i7NZNzThrDmm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "rjNUTp0XCKhKZeT6IftloPDU1lExFOYASUNy7QrgoDhemCz4rk8xDmTRY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "Ag7iHvNMhOHk40CoGROr0XO77kW5RIpCSEzFO4Fo5ukY4Ed2nOoIsUDtw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "FgpcYjxrAdQumqcPkB85yTCTgqeAwANro8ISZBxbq5YekpYktAQbYT03z"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "63B84Nj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "kZxtddDqaJgc7rOAaF9smB7mFiYzfSwHBrHwxaBE2dKlj7UexATBZFpvUYbvXbajr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "MOnwkF31XFqlNFYgBVTl4I9paqU7SVfyWpnzXNSDjbtQMnIjIoiSUq93EOuoAL2Qf"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
