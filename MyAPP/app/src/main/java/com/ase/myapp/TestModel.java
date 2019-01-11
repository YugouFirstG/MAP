package com.ase.myapp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.daobao.asus.dbbaseframe.mvp.model.BaseModel;

public class TestModel extends BaseModel implements Contract.Model{
    private PoiSearch poiSearch;
    public TestModel(Handler handler) {
        super(handler);
    }

    @Override
    public void startPoiSearch(Context context, String city, String n, PoiInfo poiInfo) {
        poiSearch=PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener=new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if(poiResult==null||poiResult.error==SearchResult.ERRORNO.RESULT_NOT_FOUND){
                    Toast.makeText(context,"未找到结果",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (poiResult.error==SearchResult.ERRORNO.NO_ERROR) {
                    Message message=new Message();
                    message.what=1;
                    message.obj=poiResult;
                    sendMessage(message);
                    Toast.makeText(context, "Poi检索" + poiResult.getTotalPageNum() + "页", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                if (poiDetailResult.error!=SearchResult.ERRORNO.NO_ERROR){
                    Toast.makeText(context,"未找到详细结果",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,poiDetailResult.getName()+":"+poiDetailResult.getAddress(),Toast.LENGTH_SHORT).show();
                    Message message=new Message();
                    message.what=2;
                    message.obj=poiDetailResult.location;
                    sendMessage(message);
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(listener);
        if(poiInfo==null) {
            poiSearch.searchInCity(new PoiCitySearchOption().city(city).keyword(n).pageNum(10));
        }else{
            poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUid(poiInfo.uid));
        }

    }

}
