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
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.aaaboundedcontext.authentication.Login;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        User user = new User();
        user.setPasswordAlgo("iH49L5AYxLrnO3bXKCcbzH4XiPRErO9squZUgr5Y3mOCkpAWu5");
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(2644);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1460543426349l));
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1460543426349l));
        user.setIsLocked(1);
        user.setIsDeleted(1);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setUserAccessCode(24260);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("yWG4b2eN4pKsb100keo6CCz73eIrjPq3VvyEwdxcl8WCuccwxo");
        useraccessdomain.setDomainIcon("7S7z3F10rMSZBK8Y9tAk2Oxw5Toi8FaWurwV53RDXNLwM2hUSq");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("CmvUuCfKujyFbCuDVdtDlstDUguidWGXYyI2Hyt7vL2zc55Qnx");
        useraccessdomain.setDomainHelp("ugq4UKcbpAva0ygdp9VT1nDfodHoyCmsRKRqhQpGRSzdpC6rZq");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("6kwzyz4qsv5siUyqOsH8DiDmnqQDBFp4eudtV926InJqmpYaef");
        useraccesslevel.setLevelIcon("T6tt8e3VaHhAhGk5vk2cy4HfUkHmCf40A7wb6xjBaq2uQ4Da6t");
        useraccesslevel.setLevelHelp("doSbUrpOQFyiedBwMEefVJ7y6dmZ1AISi8y1QIhSOAJ1UNqND6");
        useraccesslevel.setLevelName("pDYXh2H5BSxP0hvq1xLBJqtrLj3foc2KlYU6zFRyolryXr01tQ");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setPasswordAlgo("qipaj6XI3wsK1neV5V19yvGHz3Jc0lxhiBNFHLH4nVFx9oGa0n");
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(1290);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1460543426461l));
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1460543426461l));
        user.setIsLocked(1);
        user.setIsDeleted(1);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setUserAccessCode(50733);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setMultiFactorAuthEnabled(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("4sQzNpij89n44QlqM1pLRBbxIIFtR8rfWuHXWGw4Bs1VimJwpS");
        Question question = new Question();
        question.setQuestion("cstWZE1oUZS83G2aax4w3O3iOnsYn397so8Q5OLahMiAdB50RL");
        question.setQuestionIcon("1ZhulOL31eueeUpAavXQUtCx0S4xIEQ9HTlAj0pt7q6YKzXv9t");
        question.setQuestionDetails("cNdNHOEh5V");
        question.setLevelid(2);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("P1nnr411qBCwp1ZON2M0b1Zl7bz9uLdsadPsEGIelsvfQTLUeU");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePasswordExpiry(4);
        userdata.setUser(user);
        userdata.setOneTimePassword("CDrFwQPUkHYN2xFIM8M5PcLD3VIXIvli");
        userdata.setPassword("QD0hrmcaSGsMuFou2fJoo4iBPRVZOTtu18GHqu9nC94FRXkw7E");
        userdata.setLast5Passwords("yxge9igtUiywFCI1YYQnW9ksvxTPVDP5iAnmzMliQ2caR3eYqo");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1460543428174l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("BfYRmfzfhI9OvYViUJjs1e3cOSpBXfNuxVnW4bcsoCbCEIqqD4");
        corecontacts.setAge(39);
        corecontacts.setNativeTitle("240aZ9mERe20oqIIvKnimh7midOazWIV24qI5iveyDIMlEHCOv");
        corecontacts.setNativeLastName("N0tkKbxdpsUn2Y8NTeJjtnZRPVcw63QvYJiq98h1SSRm1EAXXu");
        corecontacts.setNativeFirstName("QcYIgS31rrNA2DrV5Yc6B3RycU9JRlJr8dXTeM2B26tWRv976s");
        corecontacts.setMiddleName("DeaOzwJCITeT2g4bN5TSilAs9cE9qH3Yf6vMDHiKCVOF8QLvif");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460543428597l));
        Language language = new Language();
        language.setLanguageType("f55F1SFHTqb1DMZppLSUcGoe538OG3mV");
        language.setLanguageDescription("uh2ADl9W1yKv2pBpzWdJbOBpDGAmA0ua6ZGndnzXSn6DB8BT3l");
        language.setAlpha3("e2T");
        language.setLanguage("SU5nep3DYZpzbth5bcnmZFYeR4nz4oel6iOK2RQL0yOej6k4jo");
        language.setAlpha4parentid(10);
        language.setAlpha4("kh9z");
        language.setLanguageIcon("pEaNHltxKyPE8ENc8ePYOKuxH3TenwQSA3JtUEUelOmnXlUMwk");
        language.setAlpha2("WT");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("9RdavetReLtIPPgV5bO9YDFZ3DhRKaXX4O90JXwGtMzRgp4C2A");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("hO6XCRYkag4aNuDoxrG8Ok8KIx4ba83vdyyAondWhz56N2YPU3");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("GALJYNuELvRM3TlS470tP4VoGCzJbn0e58bpOUxvj7EznQCv17");
        timezone.setTimeZoneLabel("nB8NkhQ0vZfniIH5nHdtHsuVCCFloOeiKvbNlV9HU9oY0otD7s");
        timezone.setCities("fmXEKBq8FHUaIBcEQxYlSBf0n9ZzYQ34eqDRwjRgDrarsIEtQR");
        timezone.setCountry("jeOTwtnLHTv6TAmAh8jjSp1piyXwkzSbPAJrzRE4M9KunGL9Kn");
        timezone.setUtcdifference(10);
        corecontacts.setNativeMiddleName("Al5pYKxhW6CBRW1FXEwqrvLQCMhFJnnzEDhrpxdr8LFguJNu6n");
        corecontacts.setAge(16);
        corecontacts.setNativeTitle("D3Me26hgqxeRtFG3uGY1yTcsciRAdNvZLShI6ywVSomkEaZ5Tn");
        corecontacts.setNativeLastName("Fj1WBiOcEJ7RQWJJ2y3foJsCHumG46KjM8h0jHTGh03LXiaMri");
        corecontacts.setNativeFirstName("92yaVgvDGra2N4Jr8Zc19KYW795jdE5fOdVwu6cPVRUIplDdq0");
        corecontacts.setMiddleName("ZB90yBiycaeSquYIIthooPhmZzTlUkc5Mf3GJDK3gn4h1kVN1c");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460543428719l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("DvYacHQ4geqWG2ZLcRuAah4gbw9vVJGEnilaXrXwa5SLgCmFLL");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("rr6A4PBhZa8DR48i56RSyxVN5aZNA5fnQ5hcSZzz83A2WjbJid");
        corecontacts.setPhoneNumber("dSDhPXT1rYboyWne7YMI");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setFirstName("ePjND3prytvTmss4OnPzN2L9CfhNtPqFIEg3qljAc5KkrDUCwX");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460543429542l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("ooc6GTqCRh");
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("g1X2a52s1MWZ4vf4jJvFrF7GMtyenpvTH5QdDArpIhFtbVCbBt");
        communicationgroup.setCommGroupName("OOHR9ZwZoldsaMuBizB8cPQfryEvTHmv7ASuzN204iaC3FtxU3");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("hF7a8BfxwEZG0vrl4nP3IH3JLzJraXBuwoIusnaOsKaROptY9j");
        communicationtype.setCommTypeDescription("YQT97YjKoa4orqBcGkwntBkU036TKgOdSleeZKdQL2mfpOdkJv");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("lBaiDzcte6");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        state.setStateCodeChar3("ILDQDqfc2dbZM0LNnoEexA9ylMEyc0vV");
        state.setStateCodeChar2("Plztatbc9d2o931kNZlaQFcLjMTLbVCI");
        state.setStateCapitalLongitude(2);
        state.setStateFlag("EPLklFDITgUZSVoHv5RsL3Jf1ILwd0vBQ7Yu99xSssih21he43");
        state.setStateCode(1);
        state.setStateCapital("yCSVpO4svtYUML99ZfFKl1xUTu8V4tYWZwahgpwTVrL8eUNYOg");
        Country country = new Country();
        country.setCountryCode1("hHr");
        country.setCurrencySymbol("RxNn0sTu0Fx8GsTALv8iLql0Bfb1oZZp");
        country.setCapitalLatitude(2);
        country.setCountryCode2("HIV");
        country.setCurrencyName("2fkHbm6fmtlZNDJIYkQElrLIU4Tg0REtPaQztc46O7z2OToKRh");
        country.setCapitalLongitude(4);
        country.setIsoNumeric(422);
        country.setCountryFlag("5qcOvQt6fc96Qy4mKC8RJFIiHoUDbAKRSeL8eiwHezPoD5E7K4");
        country.setCountryName("7GyJx17dtfpz7wFBw1eAN4aBXP17mX0kqdL1WxdLEjdJTDedN2");
        country.setCurrencyCode("mTW");
        country.setCapital("NpgIa21MUkEnKBEqjF97Az7dqMGvEglz");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("xv9teWmKlgMlJux2OCA2NZ4ip1SWI4ZQ");
        state.setStateCodeChar2("M7LKRDDbryg1w63xwRE0pTfs6wrVMYFD");
        state.setStateCapitalLongitude(3);
        state.setStateFlag("dLhrkXFI8QJBt7ChFdI64PYd9qO8SycIC8KbSQZRrypjUfFL2m");
        state.setStateCode(2);
        state.setStateCapital("5hhkKtlihKxnpi0L289EamRfRIBH3Iw7R0PAVG6j9NWp9HADLe");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("0eiea7sQRTSKunztI2h2larkZdJmzt5nhbZK0IpAPkLzk8je7w");
        state.setStateDescription("Cgx7ahjFVN4KPgpMZ5UHaGepX92xvtZd0LnAlekP8jcuHODEtO");
        state.setStateCapitalLatitude(10);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("X4g6a0YfWtBbTO0V83P7G6R1go6hJ5tSltkDfve321VE2ugU40");
        addresstype.setAddressTypeDesc("RQZ7J58XQOeVkV0QxyktOkT2scoVRCLjxlQz7VZpdp5qYqzRyM");
        addresstype.setAddressType("byW3D0yNF3AfxjQX9XKdgMU0uP5T9EG6atoszO0yuR2Tld5oH4");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("VHN3SRKab1jgcU7C6HzlbwAqAfVhzObltAmGLT3L9wAyWnFqQz");
        city.setCityCode(3);
        city.setCityDescription("ZZIPwvAprx9GiANSyB0DdjDYHlV4ioq6kEkuEyeSjZCNMO7oxw");
        city.setCityCodeChar2("sj2Cgsm4mhE7Ql4IN4pZvN6b2a3fsqD1");
        city.setCityLongitude(7);
        city.setCityLatitude(2);
        city.setCityName("W5hDsjJxkE2id3RPGVqHScgCKvcMsgIMx3bs3dGUWw121yMfuJ");
        city.setCityCode(3);
        city.setCityDescription("BHaLL5SE5LN1Hp71Veqehhiw3ASQt4Ux7Hm4QYi6Gthl6G5fX3");
        city.setCityCodeChar2("IL5v5ULhZHOg94b1S4Vg4mlwuNp1CWoU");
        city.setCityLongitude(7);
        city.setCityLatitude(11);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("jaUnGTuQakaJKqnuJ4P0hso1HQr0Lab6ISE3cgLhUiZBb4Atv4");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("kUgdNNB67mwevIeqtcFIrBGbnEklnlRmDVHtkRrnjxM3A1Cf7s");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("1Pab1ossNJQ");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setAddress1("SiQsVEIek7nc6mLt8g8Dr5g3NQRg9pAW4tsFg0txQSTd84JbG4");
        address.setZipcode("A28UsF");
        address.setAddress3("6v7P26lUJLaiLC6TmmWzSWql7HbSY7QvpO1WWbgRT0bokbR7YT");
        address.setLongitude("OlbOnhPZoYqqtjyovdckfgnEMlvkbOqERYvI5aQqamDgD1X1a0");
        address.setLatitude("uMajbvnb0dCWd8ngcFA7nTQlSP0opcIcbJ1AsljbvEk1D1bv82");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setServerAuthImage("2x7TkuKRS98zaSCY0n5xxNr8Wqlg0hMv");
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("b3JqD2VLMNk8EFbP");
        login.setLoginId("mhrIgaVLSWjLBZ6hPEtRc7k0ay6unYRJZ0ZyfGPIcYur4MFmXN");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setFailedLoginAttempts(1);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("EGo8dVegGrov0ggmWIW2p0i0bcUm1Yug");
            login.setServerAuthText("GXJYRYDpfeGiB8ek");
            login.setLoginId("5glSO0dZxRAmIgwwe2yuTK2DplSN2RpuX8u1v7XnIaoxNB2lkW");
            login.setVersionId(1);
            login.setFailedLoginAttempts(6);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "HkrzFlI1SE3Nwf4iBTeb2VVwqT19fxyMvZjH5Uf5ANbFKkG42kaKAXYiUeJForogh5ZGJRGltMUsLqtxbWcAg8joNiSA88SAu2m8hgxjhR2CaotEZ9L0x0lqLnGsJRE6Vi1BzPTGGyDcNzxR65YrHb7b1VmiUUfDJ33cNZ8wb1qmXxfOAXlX30LT96XV4sZkJIzdrIrV0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "MBMyY1MAbREE9kZ32Adl9KzuVMJPkO2DZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "GIUNZzownibYmDn6O"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
