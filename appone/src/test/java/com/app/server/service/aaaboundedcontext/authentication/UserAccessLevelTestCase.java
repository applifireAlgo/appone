package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
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
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("Li8I4p6J5ziiG1hyWRsXr92Cgs4PpjpdaXjTquI9UfvEPKWCef");
        useraccesslevel.setLevelIcon("o6vEsjzdZdmSaktkqnehfErnK2mZYHHdyhN2s3GV4PVaKRgDyT");
        useraccesslevel.setLevelHelp("klyeGjedEfjta6UqfQ0P3Bee0HGUVGTLrBfw1i3xroKm07m8Mk");
        useraccesslevel.setLevelName("ba9jnH1apQtN1R1OclV1JZOZ62pva4uIgIZjNeXPpCfPbLIEOS");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setUserAccessLevel(5507);
            useraccesslevel.setLevelDescription("IHlbmtYJA0fApqfI86JXwFhkoozyluyQV1wz3cOw7xY6JN3xNU");
            useraccesslevel.setLevelIcon("IcL3N35zeea36HDhHuYz28VYXe7NhXxI85yieQqDFLGtA9AC7D");
            useraccesslevel.setLevelHelp("3EWzWVqEz4DpXGzpQubXfdOWjOX9rUpFgXCe3e2NGXXg5mY4rW");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelName("oTlO9NFLSVCohPV82DBQopyHEoUo7Fbwf5J6gsOqcpA8LMyPbf");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 106307));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "1NkUM4PKsBAE8mTM8eiySQvh2trx2VfxkNOHcGfxAnINlrVJK4EjecJa5IRVxcdeH8gQJpYe4HYFbglekioVW9ZHS3dD99IulQPmfm6vbOyUHP8D0f69nX4P8s7lE9aIvc2puQwXBrWSq3qt6VQ7rdnOCFMnfxrNMohbIVC7juZpWVQfL3vvm66nIiMr80oXDLO06DP9hSJvpM1oLYGF99pt1CdShe0bJ0GOSfmSCtvHzt5L4ChCO3Bg198vmcGUW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "KcSFhpXtP2cDnamfRbfm2j5jPOxfnUhF2UDYqBEr25CCofjz8ib4vZhhKfkFNKui2qZBrf2TEhjKdNLTRKQi5LYmBHikvFT2zuqD8v885vPG9EGYGkFlBKZy8u35fenxNnk10ru384ey71M3hINU8j3sEDkE8Jkgs4dZcotqxflSav9XwdcDBgmq6HJugcAIP5sNpha6w3it84wkFtiYok3xxMAZePpAfkXPqkc5K4TBGoqBLooHqqZWkeaXzaCvs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "XiJnWDyBC3pq2SN12wa4e8SBfkOWJ55EsUOxKwAJjR8zsGziyjFf9KamTepEb8gmwc5zgiJK1dsF9EYszkgG1VZoIFB0QpyKaJGTX1xwp5jxKvOU9IcR5c3VBVVUslxeIgZYGWoTBPgUrTwgwY1cLMqJYjLuD7etIBuVktJtRVoGndcKsg2nsyjXX2z1TlOamj6ctvmFIpWjfI4qRBMLz0c0XC0ISbThq5u8k7UecJH6KbejuFLjkdm0oMu4yCBO5"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
