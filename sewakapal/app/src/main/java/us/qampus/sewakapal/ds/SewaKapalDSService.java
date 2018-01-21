
package us.qampus.sewakapal.ds;
import java.net.URL;
import us.qampus.sewakapal.R;
import buildup.ds.RestService;
import buildup.util.StringUtils;

/**
 * "SewaKapalDSService" REST Service implementation
 */
public class SewaKapalDSService extends RestService<SewaKapalDSServiceRest>{

    public static SewaKapalDSService getInstance(){
          return new SewaKapalDSService();
    }

    private SewaKapalDSService() {
        super(SewaKapalDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://pods.hivepod.io";
    }

    @Override
    protected String getApiKey() {
        return "CU8DvLmK";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://pods.hivepod.io/app/5a56edb255ad1d040016cf42",
                path,
                "apikey=CU8DvLmK");
    }

}
