

package us.qampus.sewakapal.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import buildup.ds.SearchOptions;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.StringUtils;
import buildup.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "SewaKapalDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class SewaKapalDS extends AppNowDatasource<SewaKapalDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private SewaKapalDSService service;

    public static SewaKapalDS getInstance(SearchOptions searchOptions){
        return new SewaKapalDS(searchOptions);
    }

    private SewaKapalDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = SewaKapalDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<SewaKapalDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<SewaKapalDSItem>>() {
                @Override
                public void onSuccess(List<SewaKapalDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new SewaKapalDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getSewaKapalDSItemById(id, new Callback<SewaKapalDSItem>() {
                @Override
                public void success(SewaKapalDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<SewaKapalDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<SewaKapalDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().querySewaKapalDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<SewaKapalDSItem>>() {
            @Override
            public void success(List<SewaKapalDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"name", "telp", "pengguna"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(SewaKapalDSItem item, Listener<SewaKapalDSItem> listener) {
                    
        if(item.imageUri != null){
            service.getServiceProxy().createSewaKapalDSItem(item,
                TypedByteArrayUtils.fromUri(item.imageUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createSewaKapalDSItem(item, callbackFor(listener));
        
    }

    private Callback<SewaKapalDSItem> callbackFor(final Listener<SewaKapalDSItem> listener) {
      return new Callback<SewaKapalDSItem>() {
          @Override
          public void success(SewaKapalDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(SewaKapalDSItem item, Listener<SewaKapalDSItem> listener) {
                    
        if(item.imageUri != null){
            service.getServiceProxy().updateSewaKapalDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.imageUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateSewaKapalDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(SewaKapalDSItem item, final Listener<SewaKapalDSItem> listener) {
                service.getServiceProxy().deleteSewaKapalDSItemById(item.getIdentifiableId(), new Callback<SewaKapalDSItem>() {
            @Override
            public void success(SewaKapalDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<SewaKapalDSItem> items, final Listener<SewaKapalDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<SewaKapalDSItem>>() {
            @Override
            public void success(List<SewaKapalDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<SewaKapalDSItem> items){
        List<String> ids = new ArrayList<>();
        for(SewaKapalDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
