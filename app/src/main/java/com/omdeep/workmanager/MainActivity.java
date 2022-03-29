package com.omdeep.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;

import com.omdeep.workmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

Constraints constraints = new Constraints
        .Builder()
        .setRequiresCharging(true)
        .setRequiresBatteryNotLow(false)
        .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest
                .Builder(MyWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).enqueue(request);
//TODO: Two methods to get instance of work manager
        //TODO: 1.
//        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId()).observe(this, new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(WorkInfo workInfo) {
//                binding.tvWorkStatus.setText(workInfo.getState().name());
//            }
//        });
//Todo: 2.
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId()).observe(this, workInfo -> binding.tvWorkStatus.setText(workInfo.getState().name()));
    }
}