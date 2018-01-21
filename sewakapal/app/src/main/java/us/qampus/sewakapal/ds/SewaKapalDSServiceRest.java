
package us.qampus.sewakapal.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface SewaKapalDSServiceRest{

	@GET("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS")
	void querySewaKapalDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<SewaKapalDSItem>> cb);

	@GET("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS/{id}")
	void getSewaKapalDSItemById(@Path("id") String id, Callback<SewaKapalDSItem> cb);

	@DELETE("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS/{id}")
  void deleteSewaKapalDSItemById(@Path("id") String id, Callback<SewaKapalDSItem> cb);

  @POST("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<SewaKapalDSItem>> cb);

  @POST("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS")
  void createSewaKapalDSItem(@Body SewaKapalDSItem item, Callback<SewaKapalDSItem> cb);

  @PUT("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS/{id}")
  void updateSewaKapalDSItem(@Path("id") String id, @Body SewaKapalDSItem item, Callback<SewaKapalDSItem> cb);

  @GET("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS")
    void createSewaKapalDSItem(
        @Part("data") SewaKapalDSItem item,
        @Part("image") TypedByteArray image,
        Callback<SewaKapalDSItem> cb);
    
    @Multipart
    @PUT("/app/5a56edb255ad1d040016cf42/r/sewaKapalDS/{id}")
    void updateSewaKapalDSItem(
        @Path("id") String id,
        @Part("data") SewaKapalDSItem item,
        @Part("image") TypedByteArray image,
        Callback<SewaKapalDSItem> cb);
}
