package com.example.customadapterpractice2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class ZigZagActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Integer> imgList = new ArrayList<Integer>(Arrays.asList(R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8));
    ArrayList<String> namesList = new ArrayList<>(Arrays.asList("John","Karan","Harry","Nathan","Moeen","Sonam","Sara","Tanu"));
    ArrayList<String> messagesList = new ArrayList<>(Arrays.asList(
            "Hello!",
            "How are you?",
            "I hope you're doing well.",
            "Have a great day!",
            "Goodbye!",
            "See you later!",
            "Take care!",
            "Stay safe!")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zig_zag);

        listView = findViewById(R.id.listView);
        CustomAdapter2 customAdapter2 = new CustomAdapter2(ZigZagActivity.this,imgList,namesList,messagesList);
        listView.setAdapter(customAdapter2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}