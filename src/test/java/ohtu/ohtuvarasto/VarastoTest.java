package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 1.5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void yritetaanOttaaLiikaaTavaraa() {
        varasto.lisaaVarastoon(5);
        
        assertEquals(5, varasto.otaVarastosta(6), vertailuTarkkuus);
    }
    
    @Test
    public void yritetaanLaittaaLiikaaTavaraa() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldollisellaVarastollaOikeaSaldo() {
        assertEquals(1.5, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldollisellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusEiSaaOllaAlleNolla() {
        Varasto varasto3 = new Varasto(-2);
        
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldollisenVarastonTilavuusEiSaaOllaAlleNolla() {
        Varasto varasto3 = new Varasto(-2, 1);
        
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldollisenVarastonSaldoEiSaaOllaAlleNolla() {
        Varasto varasto3 = new Varasto(2, -3);
        
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOnOikein() {
        assertEquals("saldo = 1.5, vielä tilaa 8.5", varasto2.toString());
    }
    
    @Test
    public void varastostaEiSaaOttaaAlleNollaa() {
        
        assertEquals(5, varasto.otaVarastosta(-2), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiVoiLisataAlleNollaa() {
        varasto2.lisaaVarastoon(-2); 
        
        assertEquals(1.5, varasto2.getSaldo(), vertailuTarkkuus);
    }

}