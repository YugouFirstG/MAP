package com.ase.myapp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.search.core.PoiInfo;
import com.daobao.asus.dbbaseframe.mvp.presenter.BasePresenter;

public class Testpresenter extends BasePresenter<TestModel,TestActivity> {
    public Testpresenter(TestActivity view) {
        super(view);
        Log.d("TestP","super view");
    }

    @Override
    public TestModel binModel(Handler handler) {
        Log.d("TestP","bindModel");
        return new TestModel(handler);
    }

    @Override
    public void modelResponse(Message msg) {
        switch (msg.what){
            case 1:
                mView.PoiResearch(msg);
                Log.d("P","msg");
                break;
            case 2:
                mView.PoiResearchD(msg);
                break;

        }
    }
    public void startPoiSearch(Context context, String city, String n, PoiInfo poiInfo) {
        mModel.startPoiSearch(context,city,n,poiInfo);
    }
//    public void login(String a,String b){
//        mModel.login(a,b);
//    }
}
