package com.app.server.service.organizationboundedcontext.contacts;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Language language = new Language();
        language.setLanguageType("UDxjZkNZOhyOb96qnSyjDKFHVgUJ5WZ8");
        language.setLanguageDescription("W4M7hyXXCsrGfQScrviUZ3CvMMzvJjxe4Gg6eWWXpbb9Cdv7Sc");
        language.setAlpha3("Lbh");
        language.setLanguage("hGtvZ910UnyU9dMX6FAYtG3aXJCkbZjcjoWUN0KYm2ZSEvmQUv");
        language.setAlpha4parentid(10);
        language.setAlpha4("iJG3");
        language.setLanguageIcon("TOG4t3sOMtjoRdgK2qfl2ybcYgJXAurQcBQk5DkD0MXwhpdWYJ");
        language.setAlpha2("IB");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("ad9fD8QXy732GAIG1lWsjUzDEV0CzRmfcmygCvbAkCn2hP6OJV");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("4IEMeOn8qzAjf87Iijpht8ZYs9sw9bxJThA1BsCXNFb5nUa89W");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("Fori5m3kUEaZHxtg7q7x1LjSuuKg2CHQHyayark5lVur3XODFa");
        timezone.setTimeZoneLabel("difCv4fhzxsjxcMCCrPuZ0JrfUJpRWjFOI3x7yRAopi8QHYoHb");
        timezone.setCities("2BLjF5PgRwhTVjpHdMBZMKv3BQSdr60jQtny9CrUEEMuuPnC7v");
        timezone.setCountry("f6d9PgcNNVqh6ofST9ozTXyeK3A1ovuScHIs9QgdeVm4Fabyrl");
        timezone.setUtcdifference(5);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("S1CWXsGfqibZNmMTYbHiSXydraX8K0pdo3MtPHo6IYh22vgWvv");
        corecontacts.setAge(25);
        corecontacts.setNativeTitle("uSMjR85IgnGOwHCEfyh9ibtSwW6cuj50WZb1joU1lmAeqpoL6H");
        corecontacts.setNativeLastName("94PudFzHHVXTUF2hRt60GGzVItOLcpnKyWK1suQ7YQBsr6E3MB");
        corecontacts.setNativeFirstName("BZAmSKseVlHFb79tbX6gF3Q6fslYhmeVFflVI0opU23Q2ZS7Fc");
        corecontacts.setMiddleName("PMshyEkHYJba5Zu1s0uEynDrKLOQpCNnL1QFeApH9EhnOlRMLs");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460543400526l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("MsIF3iQSsuzT4V21KPyIx6yLJDjFckRiARX19ZxmrjWVM9fDzT");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("bg5SgJEpZve6ct1sjU6NmLlPno88JXfQRxfUSCkLOCOHQZMWod");
        corecontacts.setPhoneNumber("ph3BXMLDA7EBk95rkFpJ");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setFirstName("mz41166KK6whBT3WS33728zjdxfENs5sOBP7EUP78AQg9eOlcf");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460543401414l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("cUrAYmymdp");
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("ztShefkBBQchYu8Be0xhk09FkEiGZ6pZWMjWPfLByqtly0IDmX");
        communicationgroup.setCommGroupName("TBA5vnrQO1Ho8Pgkr9wUI6gYAOXhOV4MsZwoz51tFrx73h4G9Z");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("ZBL2mRLFHFRbkrHjn0U7yPwrK5MZliswFlT86T2xJ2czbwGTRp");
        communicationtype.setCommTypeDescription("ggGGdheHyxBESeAJ41YSBt9vXJ1G9tEnuyMi42hUAwwDiUvfkt");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("Q9AYk1wsxi");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        state.setStateCodeChar3("EEKoBM5Xz7os9K0s6dATefTcrDZ0MYe7");
        state.setStateCodeChar2("fw70Mlq63Zn3JOZBc8MH8YC77Odr659N");
        state.setStateCapitalLongitude(10);
        state.setStateFlag("QxEgBonWt0KGsLBUn7YH36mJMTcpQsoyjYUeEyMX25HPLr5iBx");
        state.setStateCode(1);
        state.setStateCapital("SbpwgO0YJNUOeGTFqGYuGB88Gh5QTVM262MI8QlTVwTylkUC4s");
        Country country = new Country();
        country.setCountryCode1("AEf");
        country.setCurrencySymbol("ZF9qMdbghXxUa517btPIy0QkydFFZjyo");
        country.setCapitalLatitude(1);
        country.setCountryCode2("TpP");
        country.setCurrencyName("TCXJdb3cMwbmyiNFeH9qXRnviAFlpunT5jwoHRG4ytcsTp8kbY");
        country.setCapitalLongitude(4);
        country.setIsoNumeric(652);
        country.setCountryFlag("pCJ5r7cUobbVtCdHrFoq7sbFpDQCV7aqIpU50OpnGQ2JB6fBCO");
        country.setCountryName("qGoWn0gUTFttafOMe6bsGV0gWolwfqAiVIzoiGDbEKcpywk8xh");
        country.setCurrencyCode("4u0");
        country.setCapital("JVUXJxcICX13XLmv49oDsAR7E8vZUNhY");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("kJM5CrdKieBKoiBRyDIfmpaurC8Pi7XB");
        state.setStateCodeChar2("yRLdrVUhS18HHgvTz9fkkQqM8f1325hS");
        state.setStateCapitalLongitude(8);
        state.setStateFlag("dzTi7Knr4hQaRop5MjLn3yZBNONQ6sRMnuGrGeIWDmbzMuX9HI");
        state.setStateCode(1);
        state.setStateCapital("mIxXwK5bnVBgdogOxTQ4iZDKujzY2MJvp39ecKLlWfpi4PasB3");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("Hr4BlVvoUxcEuBtLGBTOtQbktrvmwbme3pcuo1qMAB8OangDm1");
        state.setStateDescription("OfQ3EWDvwEQi8T1DfmnwokzHOtTLOSMts22FpiLY3oRslY1VdX");
        state.setStateCapitalLatitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("YZQuJKNCLTzOZSapHZ9f1LgBiNp3H6ZgQZAz9HsGNjulgtygqA");
        addresstype.setAddressTypeDesc("qjkvd5C177KXv2MfCZGwzXIUfc8sIqrIDg7GUDghXUZcAAWr1i");
        addresstype.setAddressType("BAXuH92rimuZIztR9t95CVCxdUtwP8PLVu1VPYmr9TDvjtgPEL");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("vamw5zZo5V0LXb8uwk59AFlUHDgknMrRwwzXmbG2GmJc1Br7Vd");
        city.setCityCode(1);
        city.setCityDescription("ayIQiA49ZvovqTnpCnX74B636sDIF6HOOxyRcbLzJ6ItR8f8ZS");
        city.setCityCodeChar2("yo1MgCEIBwujw7htEehSQLWJDpqtTEiu");
        city.setCityLongitude(1);
        city.setCityLatitude(7);
        city.setCityName("nDzith1JquYqtxipOclSH3GhHwXghXG1ACIzUCI1GdscSIF54y");
        city.setCityCode(3);
        city.setCityDescription("J0roQ0i2E4Dux2HAklRSX8Y8HVMGefIJWIVieS4cSFWFT6VcWB");
        city.setCityCodeChar2("tTSsAot5DXZFnGRWUX0dhDtgVb6UfnyD");
        city.setCityLongitude(7);
        city.setCityLatitude(10);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("ORyq641K4DrhON6k7D3otuCCAy6o3y1lh6Soo8mo577CfNphUc");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("WWQhZqQSCGX8eyLNyUjTZvFSGthWW6zdtkAdtCexiv2xVqoEzR");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("0GJDZ1rs11f");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setAddress1("eiq0kdxLhoGsSrw0Q8hVcf9HdbTBERvpKTStjjDNvs3Iz5EAKb");
        address.setZipcode("vQfveX");
        address.setAddress3("ybm2igBmEdk0Cn9U0f688zkK5VpV40LXgShxg7K6eK54WHsee1");
        address.setLongitude("KpqRJ2hDzoAWDtrsSmnXCU8WDYfdy838fqkNG467PIbxPdwfYi");
        address.setLatitude("PfkJUYtz9cGZTz5hfLsejygxYBGi047Q48uoiiU0V9wbd4s5FL");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeMiddleName("EF9nC4HTVAnGRtmkDJN280YY3frPna4TPxAdE0U9FvfcMUTF87");
            corecontacts.setAge(91);
            corecontacts.setNativeTitle("r5oJAA97vmyBt9MY2YLRbRITbKAolK4WY10Q2mPYYIekFfv0W6");
            corecontacts.setNativeLastName("JCmEL8TgB4Fo3TnyKYUq26m259IfTnm2V1LTWFTo5u5e9Er5ev");
            corecontacts.setNativeFirstName("q1xkrNuF422koiyKMOeyW2azoVTiI8C2OIlL55lirGdXPOmzx2");
            corecontacts.setMiddleName("mntR2epX2067E52K9N8AbXrhQfAh1skGj0IORv4fDQ45mWieWY");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1460543404008l));
            corecontacts.setEmailId("OMqSQwmBzQBOsXlRNB9FsqleSua9Jkxdakc75KP0ywMtRG9dzU");
            corecontacts.setLastName("PFVEqYGSQgQIV8krb5HbKxaqOAE740YH1yByO2DhiKO9izGOyc");
            corecontacts.setPhoneNumber("EKp0Bt3kugLNC0KXq7Wl");
            corecontacts.setFirstName("G8AlnKcI1H5B8uLeqqUyjENj4eAbo3JXl8wyRC4xHq9wP2Hbns");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1460543404468l));
            corecontacts.setVersionId(1);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "4PVtxFZ9svxvQe6kteHG0M6Biydxi9g99hznaTTFg3VyTMwK1Ip2Y1TE10WDH3jLknqU9i6158yWxgPaVzE3AFXOGW5fdNymBeoXTuRnGPCuvYKSZ19b1m0Ah7Mp9Mqo8PQfFQInjLSJWbRVqn4NxMgZo6EqTb6iwp1PSJEwE3O5io48uoWUYOU3vQS3lADmvIJnffnYPjFRU6YE85fwXYTSEsO6arzCULESXJLJ64rWsqwbrJyJbJBaNnPaindrV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "FJf8iDuBj3WRsevRmvPTV6gqg6Ype3rWTFsj2Y3A1eSqvQe8tFmK0ZXXVR5acbJ18EB6dIGMJoDWRFyg9lFLCaS59S7rr4XqMJFWU5mWtGgsaLAdRvP3S9xjz41nd7h9YRCJE4HWUiYCnLuP461NmXCnBGk12An4Xs6i9xDBB9F7gXkGXQRQHFJGoMYOHD1hokwmOZJz79B727pfqJuSdVPgc2AEoVXXOWnMpunTRRRNekbrgitCcdCstzt3ZIfg2"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "L7zRcH77IoS82rsAuJMbBsYrYnOaoaFvDPAm8roywzf64hP70qlwg4f6vrgN25adHpGvv2IbGFE7JtQKEyHa9EhLLgpZJ6cnfA09biddCUkRF6UJeBjHMGus3YtwZP1snFRcKPxbUK2YSc3jq4THnOWglCtwsnke1weSZIQGB4pyWn2RcNlcDNRONJ0vRWEuT4moY9sjOHzB8xpYasETJkHJ2NJBaRffpytY5gJqwQkDUOEXlYeeZm0iFB4GV6Mox"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "hAjrWjO5G1cJP5lVKBwHBUXg0839nCEF3lh7RiUPzFNWTk2fyiW3X5JoFFcVFf8N5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "NC9Xo0TK7mYqzLa1kLAatPEiML7eX5Oi8sjPB9qMc54qEniWPHRFxmuTbyD6xL8U5qUPIglJC3dfrkpappETTP2Gj7sFsZCkRZUnjsEyLSXB03AW55bbkwzcrNkqQ0LlL7P4JqpG0BaxFck2rFlzykYJiA2pB99BpS8E6NtBSQB9zrt5Btnl63XhV6xufjAj30s1hUH2GnT3mnuTqJN3OXdFjrHOZGZ4IZdOaTbGW4OEzhuFaa9FrGTGDxWT3j1qH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "SPbeRJ59y4KiVpEAAhmskBLirAvOF8wMjPoqLk2Wf9S47WWCrSz3SzLbrcA4PD7tkLBqsLS4IGEPr7u0HDFscE8jVEhGkTOl5ae8RmTPPikKAS3nsGc99bPrhcxCAcLbQOumAMGPmzYEXpyPPVKUIq0GFbha60layxEq40gPsl2rUYN6sx3XtQY9MGTwpsNutaYI0sBvTRtVp7ZfxSu6ll8EKUp0MN9W5iGu3Q8BXSUuzF192SGFdd6o9mT6of0kC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "9WLczI9qCd2k6FXTAKAwugVfIWc5hpHorq4j4yTU5VRkn20itQ2VMK3GePGwEexy5T5Pp6y71dZexREVb8UxH8pEYbeiImfqPBn3TvuPdug2SqQte6UveTniAaaCDgMgYeBmvwjpiuerNq8cprRzImhMZOUBiQH4O7p3F70tqNIhxI3oavhgm4wMpFo5kprAlyO6JXKl5olb06GeQKcxyvfWzrdZja5aWSsaGHe3ZxNkC5mueHofeIZK55htmlkB7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 166));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "5JARngDRyZQeqbQ74klfinHfiDFz3lFbfRHiGX8yB7EVRQwWT6NuzgNYyCLW3biNfSgcrSCEOaQ31qMc2pa7iXU0RiBbrf3v58afgydY0IIVlOam2J5aKA2CvaeTSyvV9d6YWyFga24R8PipOMcOUclDIfI57gIvz1Qgo7W2OWYxuut09y6cQdTwHHWC5inAVMbFjP9D8oHFEibBrApMUI72uuPXFcK98Kivx4oXkAfHXMKNgKALcDpOBGgsNHn6X"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "jd1RILZWwVjhZsM8zDSB3"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
