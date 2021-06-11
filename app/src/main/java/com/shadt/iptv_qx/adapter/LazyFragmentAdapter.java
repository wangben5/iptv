package com.shadt.iptv_qx.adapter;

import java.util.List;
 
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
 
/**
 * @author zsl
 * @date 2019/11/22.
 */
public class LazyFragmentAdapter extends FragmentStatePagerAdapter {
   private List<Fragment> items;
 
   public LazyFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
      super(fm, behavior);
   }
 
   public void setItems(List<Fragment> list){
      this.items = list;
   }
 
   public List<Fragment> getItems() {
      return items;
   }
 
   @NonNull
   @Override
   public Fragment getItem(int position) {
      return items==null?null:items.get(position);
   }
 
   @Override
   public int getCount() {
      return items==null?0:items.size();
   }
}