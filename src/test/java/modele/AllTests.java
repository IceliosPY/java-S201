package modele;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAchats.class, TestArticle.class,
	TestFiltreTypeDeFromage.class, TestPanier.class,
	TestSaisieFromage.class, TestTransport.class, TestClient.class})
public class AllTests {

}
