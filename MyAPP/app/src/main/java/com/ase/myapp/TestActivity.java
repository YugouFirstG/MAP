package com.ase.myapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
<<<<<<< HEAD
=======
import android.nfc.Tag;
>>>>>>> 989cc01160c148d3b950a594ef7abe7c5359686e
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.model.LatLng;
<<<<<<< HEAD
import com.baidu.mapapi.overlayutil.BikingRouteOverlay;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
=======
>>>>>>> 989cc01160c148d3b950a594ef7abe7c5359686e
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.daobao.asus.dbbaseframe.mvp.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class TestActivity extends BaseActivity<Testpresenter> implements Contract.View, View.OnClickListener, OnGetGeoCoderResultListener, OnGetPoiSearchResultListener, OnGetRoutePlanResultListener {

public class TestActivity extends BaseActivity<Testpresenter> implements Contract.View, View.OnClickListener {
    public LocationClient locationClient;
    private MapView mapView;
    private BaiduMap baiduMap;
    private GeoCoder mSearch;
    private RoutePlanSearch search;
    private PoiSearch poiSearch;
//    private Button bt;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton floatingActionButton1;
    private EditText editText;
    @Override
    public Testpresenter binPresenter() {
        return new Testpresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationClient=new LocationClient(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map);
        mapView=findViewById(R.id.bmapView);
//        bt=findViewById(R.id.bt1);
        floatingActionButton=findViewById(R.id.flbt);
        floatingActionButton1=findViewById(R.id.flbt1);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        askforacc();
        initLocation();
        mSearch=GeoCoder.newInstance();
        search=RoutePlanSearch.newInstance();
        search.setOnGetRoutePlanResultListener(this);
        point=new LatLng(39.963175, 116.400244);
        mSearch.setOnGetGeoCodeResultListener(this);
        BitmapDescriptor bitmap=BitmapDescriptorFactory.fromResource(R.drawable.bb);
        OverlayOptions options=new MarkerOptions()
                .position(point)
                .icon(bitmap)
                .draggable(true);
        baiduMap.addOverlay(options);
        Marker marker=(Marker) baiduMap.addOverlay(options);
        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
        poiSearch=PoiSearch.newInstance();
        OnGetPoiSearchResultListener poiSearchResultListener=new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                Log.d("Poi","onGetPoi");
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

<<<<<<< HEAD
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
        BikingRouteOverlay overlay=new BikingRouteOverlay(baiduMap);
        if(bikingRouteResult.getRouteLines().size()>0){
            overlay.setData(bikingRouteResult.getRouteLines().get(0));
            overlay.addToMap();
        }

    }

    private class MyLocationListener  implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
                LatLng latLng=new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                if(isFristLoctation){
                    MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(latLng);
                    baiduMap.animateMapStatus(update);
                    MapStatusUpdate update1=MapStatusUpdateFactory.zoomTo(16f);
                    isFristLoctation=false;
                }
                MyLocationData.Builder builder=new MyLocationData.Builder();
                builder.longitude(bdLocation.getLongitude());
                builder.latitude(bdLocation.getLatitude());
                builder.accuracy(10);
                builder.direction(myCurrentX);
                MyLocationData data=builder.build();
            baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode,
                    true,mCurrentMarker,
                    0xFFA4F198,
                    0xFFA4F198));
                baiduMap.setMyLocationData(data);
        }
    }
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);
        poiSearch.searchInCity((new PoiCitySearchOption())
        .city("北京").keyword("美食").pageNum(10));
        poiSearch.destroy();
//        Button bt=findViewById(R.id.login);
//        bt.setOnClickListener(this);
    }

    private void askforacc() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(TestActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(TestActivity.this,permissions,1);
        }else{
            Log.d("Testa","requestPosition");
            mPresenter.requestPosition(locationClient);
//            bt.setOnClickListener(this);
            floatingActionButton.setOnClickListener(this);
            floatingActionButton1.setOnClickListener(this);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ACT","destroy");
        locationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
        poiSearch.destroy();
        search.destroy();
        myOrientationListener.stop();
        Intent intent=new Intent(TestActivity.this,LocationService.class);
        stopService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myOrientationListener.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ACT","pause");
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ACT","resume");
        mapView.onResume();
    }

    @Override
    public void PositionResponse(Message message) {
        baiduMap.animateMapStatus((MapStatusUpdate) message.obj);
//        pTv.setText((CharSequence) message.obj);
        Log.d("Testa","s");
    }

    @Override
    public void DispMyLocation(Message message) {
        baiduMap.setMyLocationData((MyLocationData) message.obj);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意才能使用",LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    mPresenter.requestPosition(locationClient);
                }else{
                    Toast.makeText(this,"未知错误",LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.flbt:
                PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");
                PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");
                search.bikingSearch((new BikingRoutePlanOption()).from(stNode)
                        .to(enNode).ridingType(0));
//                isFristLoctation=true;
////                myOrientationListener.start();
////                locationClient.start();
                locationClient.stop();
                locationClient=new LocationClient(getApplicationContext());
                mPresenter.requestPosition(locationClient);
                break;
            case R.id.flbt1:
//                String edtext;
//                edtext=editText.getText().toString();
                Intent intent=new Intent(TestActivity.this,InerActivity.class);
//                intent.putExtra("search",edtext);
                startActivity(intent);
                break;
                default:
                    break;
        }
}

//    @Override
//    public void onClick(View view) {
//        EditText a=findViewById(R.id.acc);
//        EditText p=findViewById(R.id.pas);
//        String ac=a.getText().toString();
//        String pa=p.getText().toString();
//        mPresenter.login(ac,pa);
//    }
}