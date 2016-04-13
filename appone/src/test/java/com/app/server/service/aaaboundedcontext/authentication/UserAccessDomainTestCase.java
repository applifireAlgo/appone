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
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("SDgy5ypXv4WvLEet6jLHokJUAZRWVFunHK73c69ycymCRLW9jW");
        useraccessdomain.setDomainIcon("BiHFudYD055whwMOHkNL0qSxUnxvZ6ty4WrwmTAbvIRIRL7bGS");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("cRPYQinIQinCp964g7oGlPCaQQCwNb8mj1HyBVIKT28shBXrGm");
        useraccessdomain.setDomainHelp("xq6Um6jfCvD865ENkheBXEQcYLmnsc3LRtDsKJSgg8iqfljRY6");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainName("Z4jmypRigLmtrTbzGJS2gthDiKbiAR0lISIytVMGu6XMoKHiaD");
            useraccessdomain.setDomainIcon("uyv7Q8LCcEatNvS5AW8WkHRz1fRerUtCJsQTjNg64TRwbbwqcu");
            useraccessdomain.setUserAccessDomain(37069);
            useraccessdomain.setDomainDescription("lZfxeTCF0JHp8zrdHy2sIFW2Q8NDXtPu9mTxNEhL6k3zPuMKjl");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("EBbbxAlqs8EvXbQkx47jHydxn2JrNYkKPWLd58xpcpxaIwgCe7");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 105674));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "XQSKqFhXQgx2ES6LzdCKCHvkqK1nryHfwCN9fdzUX7wbPyiOqnGS3XcsGK8aB5GMKQgLQg3zZEEHhIJLZtW8zsO4EefTCYzHF0Ldhb6xPIYmslcB0Gz9uzFzZoTrWcR6yxrHQJene5CDipP2Wu5uohh0HQdcDqbu6UMLJmQ4v1D2vzxczkmCHL9Jz4Poh9DIqJneYZDwLAbMWX66spdV7EgEVatoNEAe38R0ZT7uHMqNXCLzvstnwuI2GMg2vdCm3"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "1FnISiBiQfD7LF7LjIQmasQ45cE0JZEqQKRbXd3V4504GGTR3efd3nneTPAwfrKXMlBkGrVJ8OCf6rUO0QY38pGhc0vgLQ6hDEmdj1SiSk8sYU5yMy7rOo9GBineMYIFPtleXd0P4kv4Zg3J9uzbL5U215OxwweNCKMIDruK5GbyUPOD71SM890A05fklfaWxTyjLAbMzUWuRjYyeIy2cl0DH18Jy4Sxk0QqVBdtCN0vBwCZHXqUoZhMEXuvQjYtB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "qakrjz915xPYO8mwhtkz8anWsnhobiT0r9sEpSVeAaFltuvGYZlkpwYUyxRqcM1ZMPAxIbw5b16ItOgNtDonlfJfrtpkWKnHgI9YGX9udsmC4nvE9zoIfLxgRGZCczwVroLnHWGWyhItX1JJElyRHv8erDhiEY4T9dTVbitSYNUi5nMQkLEJ0RmB4sY9L6pxjF2BLpY81XBUU5kw4zottwqNCzK4geblTyB7YNZR7vUhtG3MLfPwq6PvEg4BnNkl9"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
