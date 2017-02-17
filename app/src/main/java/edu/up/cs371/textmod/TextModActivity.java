package edu.up.cs371.textmod;

/**
 * class TextModActivity
 *
 * Allow text to be modified in simple ways with button-presses.
 */
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import static edu.up.cs371.textmod.R.string.copy_name_button_text;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.lang.String;

public class TextModActivity extends ActionBarActivity implements View.OnClickListener {

    Button ClearButton;
    TextView editText;

    private Button buttonUP;
    private EditText textUP;
    private String newtext;
    private Button buttonDOWN;









    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */

    Button copyName;
    Spinner spinner;
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mod);



        buttonUP = (Button) findViewById(R.id.buttonUP);
        buttonUP.setOnClickListener(new buttonUPListener());
        textUP = (EditText) findViewById(R.id.editText);
        buttonDOWN = (Button) findViewById(R.id.buttonDOWN);
        buttonDOWN.setOnClickListener(new buttonDOWNListener());





        ClearButton = (Button)findViewById(R.id.ClearButton);
        editText = (EditText)findViewById(R.id.editText);
        ClearButton.setOnClickListener(this);
        // set instance variables for our widgets
        imageView = (ImageView)findViewById(R.id.imageView);

        // Set up the spinner so that it shows the names in the spinner array resources
        //
        // get spinner object
        spinner = (Spinner)findViewById(R.id.spinner);
        // get array of strings
        String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i,0);
            if (id == 0) id = imageIds2.getResourceId(0,0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);
        }

        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener());

        copyName = (Button)findViewById(R.id.button2);
        myEditText = (EditText)findViewById(R.id.editText);
        copyName.setOnClickListener(new copyNameButtonListener());
//        copyNameMethod(copyName, spinnerNames);




    }
    private class buttonUPListener implements View.OnClickListener {

        public void onClick(View v) {
            newtext = textUP.getText().toString();
            textUP.setText((newtext.toUpperCase()));


        }
    }

        private class buttonDOWNListener implements View.OnClickListener {

            public void onClick(View v) {
                newtext = textUP.getText().toString();//nathan
                textUP.setText((newtext.toLowerCase()));


            }

        }

    public class copyNameButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            String text = spinner.getSelectedItem().toString();
//                copyNameMethod(copyName, spinnerNames);
            String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
            int viewId = v.getId();
            if(viewId == R.id.button2)
            {
                myEditText.setText(text);
            }
//            copyNameMethod(spinnerNames);
        }
    }

////    public String copyNameMethod(Button copyName, String[] spinnerNames)
//public String copyNameMethod(String[] spinnerNames)
//    {
//        //check if spinner text is spinnerNames[0], spinnerNames[1], or spinnerNames[2]
////        if((String)copy_name_button_text.equals(spinnerNames[0]))
//        if(spinner.getText().toString() .equals(spinnerNames[0]))
//        {
////            String copyNameText = copy_name_button_text + spinnerNames[0];
//            String copyNameText = "Text goes hereSleeping Kitten";
//            return copyNameText;
//        }
//        else if((String)copy_name_button_text.equals(spinnerNames[1]))
//        {
//            String copyNameText = "Text goes hereDr. Andrew Nuxoll";
//            return copyNameText;
//        }
//        else if((String)copy_name_button_text.equals(spinnerNames[2]))
//        {
//            String copyNameText = "Text goes hereDr. Steven Vegdahl";
//            return copyNameText;
//        }
//    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_mod, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ClearButton)
        {
            editText.setText(" ");
        }
    }

    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }

}
