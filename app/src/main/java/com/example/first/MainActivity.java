package com.example.first;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvResult;
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == MoveForResultActivity.RESULT_CODE && result.getData() != null){
            int selectedValue = result.getData().getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
            tvResult.setText(String.format("Hasil : %s", selectedValue));
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        Button btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        Button btnMoveWithObjek = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObjek.setOnClickListener(this);

        Button btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);

        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);




    }

    @Override
    public void onClick(View v){
//        Intent moveintent = new Intent(MainActivity.this, MoveActivity.class);
//        startActivity(moveintent);
        if (v.getId() == R.id.btn_move_activity){
            Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
            startActivity(moveIntent);
        } else if (v.getId()==R.id.btn_move_activity_data){
            Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, " Irwan Muji Handoko Your must be can");
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 21);
            startActivity(moveWithDataIntent);
        } else if (v.getId()==R.id.btn_move_activity_object){
            Person person = new Person();
            person.setName("Irwan Muji Handoko");
            person.setAge(21);
            person.setCity("Kabupaten Sragen");
            person.setEmail("irwan.muji77@gmail.com");

            Intent moveWithObject = new Intent(MainActivity.this, MoveWithObjectActivity.class);
            moveWithObject.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
            startActivity(moveWithObject);
        } else if (v.getId()==R.id.btn_dial_number){
            String phoneNumber = "08142362853";
            Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
            startActivity(dialPhoneIntent);
        } else if (v.getId()==R.id.btn_move_for_result){
            Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
            resultLauncher.launch(moveForResultIntent);
        }

    }

}