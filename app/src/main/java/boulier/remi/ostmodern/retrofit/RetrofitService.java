package boulier.remi.ostmodern.retrofit;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public interface RetrofitService {
    String SERVICE_ENDPOINT = "http://feature-code-test.skylark-cms.qa.aws.ostmodern.co.uk:8000/";

    @GET("/api/sets/")
    Observable<ArrayList<Set>> getSets();

    @GET("/{content_url}")
    Observable<Episode> getEpisode(@Path(value = "content_url", encode = false) String contentUrl);

    @GET("/{image_url}")
    Observable<ImageDetails> getImageDetails(@Path(value = "image_url", encode = false) String imageUrl);
}
