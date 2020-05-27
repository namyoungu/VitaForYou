package com.example.vita_1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vita_1.CustomListMaker.ListAdapter;
import com.example.vita_1.CustomListMaker.TextListAdapter;

public class Select_Interst extends Fragment {

    ListView interestListView1;
    ListView interestListView2;
    ListView interestListView3;
    ListView interestListView4;
    ListView targetListView1;
    ListView targetListView2;
    ListView componentListView;
    ListAdapter listAdapter1_1;
    ListAdapter listAdapter1_2;
    ListAdapter listAdapter1_3;
    ListAdapter listAdapter1_4;
    ListAdapter listAdapter2_1;
    ListAdapter listAdapter2_2;
    TextListAdapter textListAdapter;
    MainActivity mainActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View selectInterestView = inflater.inflate(R.layout.fragment_select__interst, container, false);


        // 관심 부분별 리스트

        interestListView1 = (ListView)selectInterestView.findViewById(R.id.list_interest1);
        listAdapter1_1 = new ListAdapter();

        listAdapter1_1.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_11),"노화 방지 / 장수");
        listAdapter1_1.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_12),"순환기계");


        interestListView1.setAdapter(listAdapter1_1);

        interestListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
               String ItemName = interestListView1.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(11);
                }else if(id == 1){
                    mainActivity.setFrag(12);
                }
            }
        });

        interestListView2 = (ListView)selectInterestView.findViewById(R.id.list_interest2);
        listAdapter1_2 = new ListAdapter();
        listAdapter1_2.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_13),"면역력 강화");
        listAdapter1_2.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_14),"두뇌 / 인지력");
        interestListView2.setAdapter(listAdapter1_2);

        interestListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
                String ItemName = interestListView2.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(13);
                }else if(id == 1){
                    mainActivity.setFrag(14);
                }
            }
        });

        interestListView3 = (ListView)selectInterestView.findViewById(R.id.list_interest3);
        listAdapter1_3 = new ListAdapter();
        listAdapter1_3.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_15),"소화 기계");
        listAdapter1_3.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_16),"뼈,관절 / 연골");
        interestListView3.setAdapter(listAdapter1_3);

        interestListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
                String ItemName = interestListView3.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(15);
                }else if(id == 1){
                    mainActivity.setFrag(16);
                }
            }
        });


        interestListView4 = (ListView)selectInterestView.findViewById(R.id.list_interest4);
        listAdapter1_4 = new ListAdapter();
        listAdapter1_4.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_17),"감기 / 독감");
        listAdapter1_4.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_18),"다이어트");
        interestListView4.setAdapter(listAdapter1_4);

        interestListView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
                String ItemName = interestListView4.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(17);
                }else if(id == 1){
                    mainActivity.setFrag(18);
                }
            }
        });

        //대상별 리스트
        String[] targetList = {"유아 / 어린이","노인","여성","남성"};

        targetListView1 = (ListView)selectInterestView.findViewById(R.id.list_target1);
        listAdapter2_1= new ListAdapter();
        listAdapter2_1.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_21),"유아 / 어린이");
        listAdapter2_1.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_22),"노인");
        targetListView1.setAdapter(listAdapter2_1);

        targetListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
                String ItemName = targetListView1.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(21);
                }else if(id == 1){
                    mainActivity.setFrag(22);
                }
            }
        });


        targetListView2 = (ListView)selectInterestView.findViewById(R.id.list_target2);
        listAdapter2_2= new ListAdapter();
        listAdapter2_2.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_23),"여성");
        listAdapter2_2.addItem(ContextCompat.getDrawable(this.getActivity(),R.drawable.spl_24),"남성");
        targetListView2.setAdapter(listAdapter2_2);

        targetListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
                String ItemName = targetListView2.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(23);
                }else if(id == 1){
                    mainActivity.setFrag(24);
                }
            }
        });


        //성분 종류별 리스트

        String[] componentList = {"종합비타민","오메가 3-6-9","유산균","항산화제","칼슘","마그네숨","마카","커큐민"};

        componentListView = (ListView)selectInterestView.findViewById(R.id.list_component);
        textListAdapter = new TextListAdapter();

        for(int i=0;i<componentList.length;i++){
            textListAdapter.addItem(componentList[i]);
        }
        componentListView.setAdapter(textListAdapter);

        componentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템 이름 들고오기
                String ItemName = componentListView.getItemAtPosition(position).toString();
                mainActivity = (MainActivity)getActivity();
                if(id == 0){
                    mainActivity.setFrag(31);
                }else if(id == 1){
                    mainActivity.setFrag(32);
                }else if(id == 2){
                    mainActivity.setFrag(33);
                }else if(id == 3){
                    mainActivity.setFrag(34);
                }else if(id == 4){
                    mainActivity.setFrag(35);
                }else if(id == 5){
                    mainActivity.setFrag(36);
                }else if(id == 6){
                    mainActivity.setFrag(37);
                }else if(id == 7){
                    mainActivity.setFrag(38);
                }
            }
        });



        return selectInterestView;
    }
}
