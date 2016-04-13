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
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.State;
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
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setCountryCode1("AfC");
        country.setCurrencySymbol("8tjUAkau8ng7JeSItBCK21u64HU1gtlA");
        country.setCapitalLatitude(7);
        country.setCountryCode2("5EX");
        country.setCurrencyName("BUxIeoW5MvUyN3Vrbfgwn2WFsNYmMQkI0BpmsQ9ZmYUYWp48hG");
        country.setCapitalLongitude(2);
        country.setIsoNumeric(454);
        country.setCountryFlag("xrw1IXM3P4ppx4VYZNTIsaJIkCzFLs6TihUcIThxleQwpJwlgC");
        country.setCountryName("HJ4swwjaDNz3BEFPj4oabJmGdhbJlC2uykgde3WZeNtTDyXusV");
        country.setCurrencyCode("LM9");
        country.setCapital("B3DDBqGJOOTx81kzGJ55HD0W74vwmbG7");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCodeChar3("TgzRQyZKnQrjChJM1gSEP8YXsZk1qzCU");
        state.setStateCodeChar2("5SzQcIS3o0Z9kdlqw6x12HEO9UPhHGbj");
        state.setStateCapitalLongitude(7);
        state.setStateFlag("H13LrthRix9BlImGrE3ycL7ck97l46pGwz3eTnAqQF4tFa9pAC");
        state.setStateCode(1);
        state.setStateCapital("dMZlzXQBI7rHjSBUoZIb4aaXJXYBlUASNmBi9zu5AwEGgNxYcg");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateName("82qPuzimNVmzIId5pduTjsvqVdDjmjFzib9RXbtiDz4mcemHiL");
        state.setStateDescription("WEV4shCcoz07rmR2lzoWUmD5inTg3v919LvzwqEOlho8UbwUBY");
        state.setStateCapitalLatitude(2);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCodeChar3("C0FgLXNL5kku1FYYxkKqcvVatYiqZoOH");
            state.setStateCodeChar2("w2go3ZREFKPc6FBcPdFpRt5eSyTBqRfw");
            state.setStateCapitalLongitude(10);
            state.setVersionId(1);
            state.setStateFlag("4xCsafHqWxKBkLJWUKAB9gs8jl8Fp8nTNy2AYZYUULqa2vvqrO");
            state.setStateCode(2);
            state.setStateCapital("VN0z4JwMx65vSJeZKu5x7LJRHgcFG4u8h8SXqbeg2CSCDhylTH");
            state.setStateName("9dDZbtnJqXfzCFUG9o7ynwfUeTBO1gTHbBRBw8nPT39ybGi1Qa");
            state.setStateDescription("AJtxLnWXteQVVezzZsWy9lBaWfUTQFc6ex2N2HKbUfOoLQh5Ui");
            state.setStateCapitalLatitude(4);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "7iCJ4UzBGYKhsxbUugHGLP2o5gIIa7rtr9MmFkLpMKfgm5qfnHHBlAcmasnDWBk8qYduUAiVbTKSQwEVNi5wwE3WKZaBFrPKGrp3dbsTbZbvIqYOqdgAczWm9eXdSyblN0NKUTsmsTkMf21gVny4vE4g2K43RiCT7rxAIGozvbiPmIa6mG660lmpEA7lEKTRVw0RPMPq1gTmWYkTlJGMXbiRHnvUeIPiXcov5beA1P9StzBf7Jwc7Gx58c6mP50J7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "cDL3XtFebQuzB2Ub5p72g2GSRiC5BLGOZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "WrJMJ32IE6gri5pT1DIRUsx9aXo0kTzcV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "3v49klEp2zBh5iRnU2LwGYEUZ9sfQq6mGcvKiwGL5wJcH38um2KIAIJrAVumfoHPVopjPzLXbRHP99hOvavRZUO4Wn9jgw2Yw7y1LSK7NBhip7rd7ak06iSP0lAv8V0ndUWxqFC3cqNT0HXE230eL8daMGSGPcC4tjXFUCJqcQdcDh3DnXTOwkY2qV3ponGU2gw6c97RhjDyrI7eVQraY1RRK0nLioPxgpV5quh8Do9R4L2xXldrIvDPEl2jtsbDS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "PFov8oUhlG60X1SW2tcSunE2wpSLqfzF9mw00vj7mWqtFDbFfEYVHsk9hdyHfmapMnpfdYQ9mymojck0DFq97xHBvqu6AVW6marQUijX3iBy9b2wW6HAnJl8Z1JstTYbh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "zxgTidhAFNiihFlQus6GlxjPcgOFgkej466yrxHfz1LcTrZI0tHcVpB5R2Ll50u5P60dhVsa96JBFb9S0oRSjnAV4z5j6fbQw0l49Zo9ipKXRe2K05CkWXMzMWwkWZdxP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 18));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
