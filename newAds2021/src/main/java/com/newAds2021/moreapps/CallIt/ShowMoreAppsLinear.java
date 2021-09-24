package com.newAds2021.moreapps.CallIt;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.newAds2021.R;
import com.newAds2021.adsmodels.AdsPrefernce;
import com.newAds2021.adsmodels.AppsDetails;
import com.newAds2021.adsmodels.ConstantAds;
import com.newAds2021.moreapps.adapter.MoreappsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowMoreAppsLinear {

    Context context;
    String theme;
    RecyclerView rvapps;
    ShimmerFrameLayout shimmerGrid, shimmerLinear;
    TextView tv_seemore;
    LinearLayout ll_moreApps;
    LinearLayout moreView;
    ViewGroup view;
    public static int installTextColor = 0;
    public static int appNameTextColor = 0;
    public static Drawable adBackground = null;
    public static Drawable installButtonBackground = null;
    public static Drawable rvBackground = null;
    public static Drawable itemBackground = null;
    List<AppsDetails> appsDetailsArrayList;

    public ShowMoreAppsLinear(Context context, String theme, ViewGroup view) {
        this.context = context;
        this.theme = theme;
        this.view = view;
        LayoutInflater inflater = LayoutInflater.from(context);
        moreView = (LinearLayout) inflater.inflate(R.layout.layout_more_apps, view, false);
        showMore(view);
        if (theme.equals("light")) {
            ConstantAds.isLightTheme = true;
        } else {
            ConstantAds.isLightTheme = false;
        }

    }

    public void setRvBackground(Drawable drawable) {
        if (drawable != null) {
            rvapps.setBackground(rvBackground);
        }
    }

    public void setSeeMoreButtonBackground(Drawable drawable) {
        if (drawable != null) {
            tv_seemore.setBackground(drawable);
        }
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null) {
            itemBackground = drawable;
        }
    }

    public void setSeeMoreButtonTextColor(int color) {
        tv_seemore.setTextColor(color);
    }

    public void setAdbackground(Drawable drawable) {
        adBackground = drawable;
    }

    public void setInstallButtonBackground(Drawable drawable) {
        installButtonBackground = drawable;
    }

    public void setInstallButtonTextColor(int color) {
        installTextColor = color;
    }

    public void setAppNameTextColor(int color) {
        appNameTextColor = color;
    }

    public static Drawable getAdBackground() {
        return adBackground;
    }

    public static Drawable getInstallButtonBackground() {
        return installButtonBackground;
    }

    public static int getInstallTextColor() {
        return installTextColor;
    }

    public static int getAppNameTextColor() {
        return appNameTextColor;
    }

    public static Drawable getItemBackground() {
        return itemBackground;
    }

    public void showMore(ViewGroup view) {
        view.addView(moreView);
        tv_seemore = moreView.findViewById(R.id.tv_seemore);
        ll_moreApps = moreView.findViewById(R.id.ll_moreApps);

//        tv_seemore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, MoreAppsActivity.class));
//            }
//        });

        rvapps = moreView.findViewById(R.id.rvapps);
        shimmerGrid = moreView.findViewById(R.id.shimmerGrid);
        shimmerLinear = moreView.findViewById(R.id.shimmerLinear);

        shimmerGrid.setVisibility(View.VISIBLE);
        shimmerLinear.setVisibility(View.GONE);
        rvapps.setVisibility(View.GONE);
        AdsPrefernce adsPrefernce = new AdsPrefernce(context);

        if (adsPrefernce.isInHouseAdLoaded()){
            shimmerGrid.setVisibility(View.GONE);
            shimmerLinear.setVisibility(View.GONE);
            rvapps.setVisibility(View.VISIBLE);
            appsDetailsArrayList = new ArrayList<>();
            appsDetailsArrayList = adsPrefernce.getMoreApps();
            MoreappsAdapter adapter = new MoreappsAdapter(context, appsDetailsArrayList, "linear", theme, getAppNameTextColor(), getInstallButtonBackground(), getInstallTextColor(), getAdBackground(), getItemBackground());
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            rvapps.setLayoutManager(layoutManager);
            rvapps.setItemAnimator(new DefaultItemAnimator());
            rvapps.setAdapter(adapter);
//            adapter.notifyDataSetChanged();

        }else {
            shimmerGrid.setVisibility(View.VISIBLE);
            shimmerLinear.setVisibility(View.GONE);
            rvapps.setVisibility(View.GONE);
        }
//        API.moreAppsList().getAppsDetails().enqueue(new retrofit2.Callback<MoreApps>() {
//            @Override
//            public void onResponse(@NonNull Call<MoreApps> call, @NonNull Response<MoreApps> response) {
//
//
////                    ConstantAds.category.clear();
////                    for (int i = 0; i < moreApps.getAppsDetailsList().size(); i++) {
////                        if (!checkCategory(ConstantAds.category, moreApps.getAppsDetailsList().get(i).getCategory())) {
////                            ConstantAds.category.add(moreApps.getAppsDetailsList().get(i).getCategory());
////                        }
////                    }
////
////                    ConstantAds.arraylist.clear();
////                    for (int i = 0; i < ConstantAds.category.size(); i++) {
////                        ArrayList<AppsDetails> arrayList = new ArrayList();
////                        ConstantAds.arraylist.add(arrayList);
////                    }
////
////                    for (int j = 0; j < moreApps.getAppsDetailsList().size(); j++) {
////
////                        for (int i = 0; i < ConstantAds.category.size(); i++) {
////                            if (ConstantAds.category.get(i).equals(moreApps.getAppsDetailsList().get(j).getCategory())) {
////                                ConstantAds.arraylist.get(i).add(new AppsDetails(moreApps.getAppsDetailsList().get(j).getId(),
////                                        moreApps.getAppsDetailsList().get(j).getAppIcon(),
////                                        moreApps.getAppsDetailsList().get(j).getAppName(),
////                                        moreApps.getAppsDetailsList().get(j).getAppLink(),
////                                        moreApps.getAppsDetailsList().get(j).getShowAds(),
////                                        moreApps.getAppsDetailsList().get(j).getCategory(),
////                                        moreApps.getAppsDetailsList().get(j).getOpenIn(),
////                                        moreApps.getAppsDetailsList().get(j).getOnOff()));
////                            }
////                        }
////                    }
//                }
//
//
//            @Override
//            public void onFailure(Call<MoreApps> call, Throwable t) {
//
//            }
//        });
    }

    public boolean checkCategory(ArrayList<String> category, String str) {
        for (int i = 0; i < category.size(); i++) {
            if (str.equals(category.get(i))) {
                return true;
            } else if (i == category.size()) {
                return false;
            }
        }
        return false;
    }
}
