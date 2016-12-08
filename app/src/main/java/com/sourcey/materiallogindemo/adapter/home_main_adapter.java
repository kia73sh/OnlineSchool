//package com.sourcey.materiallogindemo.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.sourcey.materiallogindemo.Models.Restaurant;
//import com.sourcey.materiallogindemo.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**import com.example.android.common.view.SlidingTabLayout;
// * Created by root on 3/18/15.
// */
//public class home_main_adapter extends BaseAdapter {
//    private Activity activity;
//    private LayoutInflater inflater;
//    public View Cb ;
//    //private List <Friends> movieItems;
//    public List<Restaurant> newsItem ;
//    private ArrayList<Restaurant> newsItemOriginal;
//    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//
//    public home_main_adapter(Activity activity, List<Restaurant> CItem) {
//        this.activity = activity;
//        this.newsItem = CItem;
//        this.newsItemOriginal = new ArrayList<Restaurant>() ;
//        this.newsItemOriginal.addAll(newsItem);
//
//    }
//
//
//
//    @Override
//    public int getCount() {
//        return  newsItem.size();
//    }
//
//    @Override
//    public Object getItem(int location) {
//        return newsItem.get(location);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public void getFilter(String fl) {
//        newsItem.clear();
//         if (fl.length() == 0) {
//             newsItem.addAll(newsItemOriginal);
//             } else {
//             for (Restaurant nw : newsItemOriginal) {
//                 if (nw.getName().contains(fl)) {
//                     newsItem.add(nw);
//                     }
//                 }
//             }
//         notifyDataSetChanged();
//         }
//
//
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        Cb = convertView;
//        if (inflater == null)
//            inflater = (LayoutInflater) activity
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null)
//            convertView = inflater.inflate(R.layout.home_main_list_item, null);
//        TextView Title = (TextView) convertView.findViewById(R.id.newstitle);
//        final Restaurant m = newsItem.get(position);
//        Title.setText(m.getName()) ;
//        ImageView site = (ImageView) convertView.findViewById(R.id.siteicon) ;
//
//
//
//
//
//         site.setImageDrawable( convertView.getResources().getDrawable(R.drawable.mored));
//
//
//        return convertView;
//    }
//}