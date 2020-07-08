package vn.edu.ntu.truonghoangkhoa.baikiemtraontap1;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Calendar;

public class dienthongtinFragment extends Fragment {

    EditText edtten, edtngaysinh, edtsdt, edtdiachi;
    ImageView imglich;
    RadioButton rbtm, rbvdt, rbnh;
    Spinner spinner;
    Button btndky;

    String ten, ngaysinh, sdt, diachi, dichvu, phuongthuc;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dienthongtin, container, false);
        addview(view);
        data();
        addevent();
        return view;
    }

    private void addevent() {
        btndky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = edtten.getText().toString();
                ngaysinh = edtngaysinh.getText().toString();
                sdt = edtsdt.getText().toString();
                diachi = edtdiachi.getText().toString();

                if (rbtm.isChecked())
                {
                    phuongthuc = "Tiền mặt";
                }

                if (rbnh.isChecked())
                {
                    phuongthuc = "Ngân hàng";
                }

                if (rbvdt.isChecked())
                {
                    phuongthuc = "Ví điện tử";
                }

                dichvu = spinner.getSelectedItem().toString();

                Bundle data = new Bundle();
                data.putString("ten",ten);
                data.putString("ngaysinh",ngaysinh);
                data.putString("sdt",sdt);
                data.putString("diachi",diachi);
                data.putString("dichvu",dichvu);
                data.putString("phuongthuc",phuongthuc);
                navController.navigate(R.id.action_dienthongtinFragment_to_hienthiFragment,data);
            }
        });
    }

    private void data() {
        imglich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(year)
                                .append("-")
                                .append(++month)
                                .append("-")
                                .append(dayOfMonth);
                        edtngaysinh.setText(builder.toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


//tao mang chua gia tri trong spinner
        String[] dichvu= new String[]{"Truyền hình số", "Truyền hình cáp","FPT"};

//tao adapter cho mang gia tri
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(dienthongtinFragment.this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item,dichvu);

//gan adapter cho spinner
        spinner.setAdapter(arrayAdapter);
    }

    private void addview(View view) {
        edtten = view.findViewById(R.id.edtten);
        edtngaysinh = view.findViewById(R.id.edtngay);
        edtsdt = view.findViewById(R.id.edtsdt);
        edtdiachi = view.findViewById(R.id.edtdiachi);
        imglich = view.findViewById(R.id.imglich);
        rbtm = view.findViewById(R.id.rbtm);
        rbnh = view.findViewById(R.id.rbnganhang);
        rbvdt = view.findViewById(R.id.rbvidt);
        spinner = view.findViewById(R.id.spinner);
        btndky = view.findViewById(R.id.btndky);

        navController = NavHostFragment.findNavController(dienthongtinFragment.this);
    }
}