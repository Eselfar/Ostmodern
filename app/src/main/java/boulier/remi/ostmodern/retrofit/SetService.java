package boulier.remi.ostmodern.retrofit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public interface SetService {
    String SERVICE_ENDPOINT = "http://feature-code-test.skylark-cms.qa.aws.ostmodern.co.uk:8000/";

    @GET("/api/sets/")
    Observable<ArrayList<Set>> getSets();
}
