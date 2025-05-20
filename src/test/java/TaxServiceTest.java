
import com.taxapp.dao.TaxRecordDAO;
import com.taxapp.model.TaxRecord;
import com.taxapp.model.TaxType;
import com.taxapp.service.TaxService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxServiceTest {

    private TaxService taxService;

    @BeforeEach
    public void setUp() {
        TaxRecordDAO dao = new TaxRecordDAO();
        taxService = new TaxService(dao);
    }

    @Test
    public void testGetBestTaxRecord_PrioritizesDailyOverOthers() {
        TaxRecord record = taxService.getBestTaxRecord("Copenhagen", LocalDate.of(2025, 1, 1));
        assertNotNull(record);
        assertEquals(0.1, record.getRate(), 0.0001);
        assertEquals(TaxType.DAILY, record.getTaxType());
    }

    @Test
    public void testGetBestTaxRecord_UsesMonthlyIfNoDaily() {
        TaxRecord record = taxService.getBestTaxRecord("Copenhagen", LocalDate.of(2025, 5, 2));
        assertNotNull(record);
        assertEquals(0.4, record.getRate(), 0.0001);
        assertEquals(TaxType.MONTHLY, record.getTaxType());
    }

    @Test
    public void testGetBestTaxRecord_UsesYearlyIfNoMonthlyOrDaily() {
        TaxRecord record = taxService.getBestTaxRecord("Copenhagen", LocalDate.of(2025, 7, 10));
        assertNotNull(record);
        assertEquals(0.2, record.getRate(), 0.0001);
        assertEquals(TaxType.YEARLY, record.getTaxType());
    }

    @Test
    public void testGetBestTaxRecord_ReturnsNullIfNoMatch() {
        TaxRecord record = taxService.getBestTaxRecord("Copenhagen", LocalDate.of(2024, 1, 1));
        assertNull(record);
    }

    @Test
    public void testAddTaxRecord_AddsSuccessfully() {
        taxService.addTaxRecord("TestTown", LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30), "MONTHLY", 0.3);
        TaxRecord record = taxService.getBestTaxRecord("TestTown", LocalDate.of(2025, 6, 15));
        assertNotNull(record);
        assertEquals(0.3, record.getRate(), 0.0001);
        assertEquals(TaxType.MONTHLY, record.getTaxType());
    }
}