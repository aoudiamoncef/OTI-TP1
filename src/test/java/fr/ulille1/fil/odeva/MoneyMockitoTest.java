package fr.ulille1.fil.odeva;

import org.junit.Before;
import org.junit.Test;

import static fr.ulille1.fil.odeva.MoneyOps.setMoneyFactory;
import static fr.ulille1.fil.odeva.MoneyOps.simpleAdd;
import static fr.ulille1.fil.odeva.MoneyOps.simpleSub;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/*
 * Unit test for simple App.
 */
public class MoneyMockitoTest {

    private Money real_M1EUR = new Money(1,"EUR");
    private Money real_M2EUR = new Money(2,"EUR");
    private Money real_M3CHF = new Money(3,"CHF");

    private Money mock_M1EUR = mock(Money.class);
    private Money mock_M2EUR = mock(Money.class);
    private Money mock_M3CHF = mock(Money.class);

    private Money spy_M1EUR = spy(real_M1EUR);
    private Money spy_M2EUR = spy(real_M2EUR);
    private Money spy_M3CHF = spy(real_M2EUR);

    private MoneyFactory mf;




    @Before
    public void init() throws UnexistingCurrencyException {
        when(mock_M1EUR.getValue()).thenReturn(1);
        when(mock_M1EUR.getCurrency()).thenReturn("EUR");
        doReturn(1).when(mock_M1EUR).getValue();
        doReturn("EUR").when(mock_M1EUR).getCurrency();

        when(mock_M2EUR.getValue()).thenReturn(2);
        when(mock_M2EUR.getCurrency()).thenReturn("EUR");
        doReturn(2).when(mock_M2EUR).getValue();
        doReturn("EUR").when(mock_M2EUR).getCurrency();

        when(mock_M3CHF.getValue()).thenReturn(3);
        when(mock_M3CHF.getCurrency()).thenReturn("CHF");
        doReturn(3).when(mock_M3CHF).getValue();
        doReturn("CHF").when(mock_M3CHF).getCurrency();

        when(spy_M1EUR.getValue()).thenReturn(1);
        when(spy_M1EUR.getCurrency()).thenReturn("EUR");
        doReturn(1).when(spy_M1EUR).getValue();
        doReturn("EUR").when(spy_M1EUR).getCurrency();

        when(spy_M2EUR.getValue()).thenReturn(2);
        when(spy_M2EUR.getCurrency()).thenReturn("EUR");
        doReturn(2).when(spy_M2EUR).getValue();
        doReturn("EUR").when(spy_M2EUR).getCurrency();

        when(spy_M3CHF.getValue()).thenReturn(2);
        when(spy_M3CHF.getCurrency()).thenReturn("CHF");
        doReturn(2).when(spy_M3CHF).getValue();
        doReturn("CHF").when(spy_M3CHF).getCurrency();


        mf=spy(MoneyFactory.getDefaultFactory());
        setMoneyFactory(mf);


    }


    /**
     * test equal
     */
    @Test
    public void testEqual() throws UnexistingCurrencyException,NonPositiveValueException {
        assertTrue(spy_M1EUR.equals(spy_M1EUR));
        assertFalse(spy_M1EUR.equals(spy_M2EUR));
        assertFalse(spy_M1EUR.equals(mock_M1EUR));
        assertFalse(spy_M1EUR.equals(real_M1EUR));
        assertFalse(mock_M1EUR.equals(real_M1EUR));
        assertTrue(real_M1EUR.equals(real_M1EUR));
        assertFalse(real_M1EUR.equals(real_M2EUR));
    }


    /**
     * test MoneyAdd
     */
    @Test
    public void MoneyAddTest() throws UnexistingCurrencyException,NonPositiveValueException {

        Money expected = mf.createMoney(3, "EUR");

        Money realResult = simpleAdd(real_M1EUR,real_M2EUR);
        assertEquals(expected,realResult);

        Money mockResult = simpleAdd(mock_M1EUR,mock_M2EUR);
        assertEquals(expected,mockResult);

        Money spyResult = simpleAdd(spy_M1EUR,spy_M2EUR);
        assertEquals(expected,spyResult);

        Money realmockResult = simpleAdd(real_M1EUR,mock_M2EUR);
        assertEquals(expected,realmockResult);

        Money realspyResult = simpleAdd(real_M1EUR,spy_M2EUR);
        assertEquals(expected,realspyResult);

        Money mockspyResult = simpleAdd(mock_M1EUR,spy_M2EUR);
        assertEquals(expected,mockspyResult);

    }

    /**
     * test MoneyAddFail
     */
    @Test(expected=IncompatibleCurrencyException.class)
    public void MoneyAddFailRealRealTest() throws UnexistingCurrencyException, NonPositiveValueException {

        simpleAdd(real_M1EUR,real_M3CHF);
        simpleAdd(mock_M1EUR,mock_M3CHF);
        simpleAdd(spy_M1EUR,spy_M3CHF);
        simpleAdd(real_M1EUR,mock_M3CHF);
        simpleAdd(real_M1EUR,spy_M3CHF);
        simpleAdd(mock_M1EUR,spy_M3CHF);

    }

    @Test(expected = IncompatibleCurrencyException.class)
    public void MoneyAddFailMockMockTest() throws UnexistingCurrencyException, NonPositiveValueException {
        simpleAdd(mock_M1EUR, mock_M3CHF);
    }

    @Test(expected = IncompatibleCurrencyException.class)
    public void MoneyAddFailSpySpyTest() throws UnexistingCurrencyException, NonPositiveValueException {
        simpleAdd(spy_M1EUR, spy_M3CHF);
    }

    @Test(expected = IncompatibleCurrencyException.class)
    public void MoneyAddFailRealMockTest() throws UnexistingCurrencyException, NonPositiveValueException {
        simpleAdd(real_M1EUR, mock_M3CHF);
    }

    @Test(expected = IncompatibleCurrencyException.class)
    public void MoneyAddFailRealSpyTest() throws UnexistingCurrencyException, NonPositiveValueException {
        simpleAdd(real_M1EUR, spy_M3CHF);
    }

    @Test(expected = IncompatibleCurrencyException.class)
    public void MoneyAddFailMockSpyTest() throws UnexistingCurrencyException, NonPositiveValueException {
        simpleAdd(mock_M1EUR, spy_M3CHF);
    }

    /**
     * test MoneySub
     */
    @Test
    public void MoneySubTest() throws UnexistingCurrencyException, NonPositiveResultException,NonPositiveValueException {

        Money expected = mf.createMoney(1, "EUR");

        Money realResult = simpleSub(real_M2EUR,real_M1EUR);
        assertEquals(expected,realResult);

        Money mockResult = simpleSub(mock_M2EUR,mock_M1EUR);
        assertEquals(expected,mockResult);

        Money spyResult = simpleSub(spy_M2EUR,spy_M1EUR);
        assertEquals(expected,spyResult);

        Money realmockResult = simpleSub(mock_M2EUR,real_M1EUR);
        assertEquals(expected,realmockResult);

        Money realspyResult = simpleSub(spy_M2EUR,real_M1EUR);
        assertEquals(expected,realspyResult);

        Money mockspyResult = simpleSub(spy_M2EUR,mock_M1EUR);
        assertEquals(expected,mockspyResult);

    }




    /**
     * test MoneySubFail
     */
    @Test(expected=NonPositiveResultException.class)
    public void MoneySubFailRealRealTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
        simpleSub(real_M1EUR,real_M2EUR);
    }

    @Test(expected = NonPositiveResultException.class)
    public void MoneySubFailMockMockTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
        simpleSub(mock_M1EUR,mock_M2EUR);
    }

    @Test(expected = NonPositiveResultException.class)
    public void MoneySubFailSpySpyTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
        simpleSub(spy_M1EUR,spy_M2EUR);
    }

    @Test(expected = NonPositiveResultException.class)
    public void MoneySubFailRealMockTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
        simpleSub(real_M1EUR,mock_M2EUR);
    }

    @Test(expected = NonPositiveResultException.class)
    public void MoneySubFailRealSpyTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
        simpleSub(real_M1EUR,spy_M2EUR);
    }

    @Test(expected = NonPositiveResultException.class)
    public void MoneySubFailMockSpyTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
        simpleSub(mock_M1EUR,spy_M2EUR);
    }


    /**
     * test MoneyFactory
     */
    @Test
    public void MoneyFactoryTest() throws UnexistingCurrencyException,NonPositiveValueException {

        MoneyFactory mockFactory = mock(MoneyFactory.class);
        when(mockFactory.createMoney(anyInt(),anyString())).thenCallRealMethod();
        mockFactory.createMoney(3, "EUR");

        verify(mockFactory).createMoney(3, "EUR");
        verify(mockFactory, atLeastOnce()).createMoney(3, "EUR");
        verify(mockFactory, times(1)).createMoney(3, "EUR");
        verify(mockFactory, atMost(1)).createMoney(3, "EUR");

    }




}
