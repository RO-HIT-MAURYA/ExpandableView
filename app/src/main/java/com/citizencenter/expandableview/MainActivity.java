package com.citizencenter.expandableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableLayout expandableLayout = findViewById(R.id.expadable_layout);
        expandableLayout.setRenderer(new ExpandableLayout.Renderer<PhoneCategory,Phone>()
        {
            @Override
            public void renderParent(View view, PhoneCategory phoneCategory, boolean isExpanded, int i)
            {
                ((TextView)view.findViewById(R.id.textViewParentName)).setText(phoneCategory.name);
                view.findViewById(R.id.arrow).setBackgroundResource(isExpanded?R.drawable.arrow_up:R.drawable.arrow_down);
            }

            @Override
            public void renderChild(View view, Phone phone, int parentPosition, int childPosition)
            {
                ((TextView)view.findViewById(R.id.textViewChildName)).setText(phone.name);
                view.findViewById(R.id.textViewChildName).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, ""+((TextView)view.findViewById(R.id.textViewChildName)).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        expandableLayout.addSection(getSection());
        expandableLayout.addSection(getSection());
        expandableLayout.addSection(getSection());
    }

    private Section<PhoneCategory,Phone> getSection()
    {
        Section<PhoneCategory,Phone> section = new Section<>();
        PhoneCategory phoneCategory = new PhoneCategory("Phone");

        List<Phone> phoneList = new ArrayList<>();
        for (int i=1;i<=10;i++)
            phoneList.add(new Phone("Phone"+i));

        section.parent = phoneCategory;
        section.children.addAll(phoneList);
        return section;
    }
}
