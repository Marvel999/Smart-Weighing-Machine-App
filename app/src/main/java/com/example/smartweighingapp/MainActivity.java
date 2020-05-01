package com.example.smartweighingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.SystemClock;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

        // GUI Components
        //private TextView mBluetoothStatus;
        private TextView mReadBuffer;
        private Button mScanBtn;
        private Button mOffBtn;
        private Button mListPairedDevicesBtn;
        private Button mDiscoverBtn;
        private BluetoothAdapter mBTAdapter;
        private Set<BluetoothDevice> mPairedDevices;
        private ArrayAdapter<String> mBTArrayAdapter;
        private ListView mDevicesListView;
        private ImageView mLED1;
         ImageView lock;
         EditText brandedit;
         TextView sine;
         TextView unit;
         Button mode;
         ImageView left;
         Button reset;

    int b=0;


        private Handler mHandler; // Our main handler that will receive callback notifications
        private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
        private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path

        private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier


        // #defines for identifying shared types between calling functions
        private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
        private final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
        private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status




    public  void Sapration(String s){

        Log.e("MY TAG", "CH = " +s);

        //public static void main(String []args){
         //String test = "st , -0000008 kg";

        //sine.setText("");
        //unit.setText(" ");
        //mReadBuffer.setText(" ");
         String stand = "" , arth = "" , number = "" , unit_1 = "",priviousString="";


        //Log.e("Sapration String got"," s = "+s );

        //if(s!=priviousString)


         for (int i = 0; i < s.length(); i++) {
             char ch = s.charAt(i);
             if (Character.isWhitespace(ch))
                 continue;

             if(Character.isLetter(ch)) {
                 if(!number.isEmpty() && Character.isDigit(number.charAt(0)))
                 {
                 unit_1 = unit_1 + ch;
                 //Log.e("MY TAG", "CH = " + ch);
                 }
                 else
                 stand = stand + ch;
                // test = test.substring(i+1, test.length());
                 //System.out.println(unit + " " + ch);
             }
             else if(ch == '-' || ch == '+') {
                 arth = ch + "";
                 // test = test.substring(i, test.length());

             }
             //||(!number.isEmpty() && ch==',')

         }

         //System.out.println(stand);
         //System.out.println(arth);
         //System.out.println(number);
         //System.out.println(unit);
        mReadBuffer.setText(s+"\n");
         sine.setText(arth+"\n");
         unit.setText(unit_1+"\n");

          priviousString=s;

        Log.i("the number","\n"+s);
        Log.i("the unit","\n"+unit_1);
        Log.i("the sine","\n"+arth);



    }

















    public void Lock(View view){

        if(b==1) {
            brandedit.setFocusable(true);
            brandedit.setFocusableInTouchMode(true);
            brandedit.setClickable(true);
            brandedit.setEnabled(true);


            lock.setImageResource(R.drawable.lock_1);

            Log.e("TEXT", "Lock Can");
            Toast toast = Toast.makeText(getApplicationContext(), "Now text is lock of edit click once more!", Toast.LENGTH_LONG);
            toast.show();

            b=0;
        }

        else if(b==0){

            brandedit.setFocusable(false);
            brandedit.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            brandedit.setClickable(false);
            //brandedit.onEditorAction(EditorInfo.IME_ACTION_DONE);
            //CloseKwybord();
            brandedit.setEnabled(false);

            Toast toast = Toast.makeText(getApplicationContext(), "Now text is Unlock you can edit!", Toast.LENGTH_LONG);
            toast.show();

            lock.setImageResource(R.drawable.lock);
            b=1;
        }
    }




    /*TextView digitelText;
    Button mode;
    ImageView right;
    ImageView left;


    int b=0;


      public void CloseKwybord(){

          View view =this.getCurrentFocus();

          if(view != null){

              InputMethodManager imm = (InputMethodManager)
                      getSystemService(Activity.INPUT_METHOD_SERVICE);
              imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
          }
      }





    public void Reset(View view){

        digitelText.setText("00000.000");
        Log.e("click reset button","yes clicked");
        Toast toast = Toast.makeText(getApplicationContext(), "You have click Reset Button!Done",Toast.LENGTH_LONG);
        toast.show();
    }




    public void Right(View view){


        Log.e("click Right button","yes clicked");
        Toast toast = Toast.makeText(getApplicationContext(), "You have click Right Button!Done",Toast.LENGTH_LONG);
        toast.show();
    }






    public void Left(View view){


        Log.e("click Left button","yes clicked");
        Toast toast = Toast.makeText(getApplicationContext(), "You have click Left Button!Done",Toast.LENGTH_LONG);
        toast.show();
    }


    public void Mode(View view){

        Log.e("click reset","yes clicked");
        Toast toast = Toast.makeText(getApplicationContext(), "You have click Mode Button!Done",Toast.LENGTH_LONG);
        toast.show();
    }*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Typeface tf = Typeface.createFromAsset(getAssets(),"font/digital_7.ttf");

        /*digitelText=(TextView)findViewById(R.id.digitalScreen);
        mode=(Button)findViewById(R.id.modebutton);
        reset=(Button)findViewById(R.id.resetbutton);
        right=(ImageView)findViewById(R.id.rightbutton);
        left=(ImageView)findViewById(R.id.leftbutton);
        brandedit=(EditText)findViewById(R.id.editbrand);
        */
        //digitelText.setTypeface(tf);

        reset=(Button)findViewById(R.id.resetbutton);
        left=(ImageView)findViewById(R.id.leftbutton);
        mode=(Button)findViewById(R.id.modebutton);

        //mBluetoothStatus = (TextView)findViewById(R.id.bluetoothStatus);
        sine=(TextView)findViewById(R.id.sinetextview);
        unit=(TextView)findViewById(R.id.unittextview);
        brandedit=(EditText)findViewById(R.id.editbrand);
        lock=(ImageView)findViewById(R.id.lockbrand);
        mReadBuffer = (TextView) findViewById(R.id.digitalScreen);
        mScanBtn = (Button)findViewById(R.id.scan);
        mOffBtn = (Button)findViewById(R.id.off);
        mDiscoverBtn = (Button)findViewById(R.id.discover);
        mListPairedDevicesBtn = (Button)findViewById(R.id.PairedBtn);
        mLED1 = (ImageView)findViewById(R.id.rightbutton);

        mBTArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio

        mDevicesListView = (ListView)findViewById(R.id.devicesListView);
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);




        mHandler = new Handler(){
            public void handleMessage(android.os.Message msg){
                if(msg.what == MESSAGE_READ){


                    String readMessage = null;

                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");

                        Log.i( "handleMessage: ",""+(byte[]) msg.obj);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Sapration(readMessage);

                }

                if(msg.what == CONNECTING_STATUS){
                    if(msg.arg1 == 1){

                        Toast toast = Toast.makeText(getApplicationContext(), "Connected to Device: " + (String)(msg.obj), Toast.LENGTH_LONG);
                        toast.show();
                    }
                        //mBluetoothStatus.setText("Connected to Device: " + (String)(msg.obj));
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Connection filed", Toast.LENGTH_LONG);
                        toast.show();

                    }
                        //mBluetoothStatus.setText("Connection Failed");
                }
            }
        };

        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            Toast toast = Toast.makeText(getApplicationContext(), "Status: Bluetooth not found", Toast.LENGTH_LONG);
            toast.show();
            //mBluetoothStatus.setText("Status: Bluetooth not found");
            Toast.makeText(getApplicationContext(),"Bluetooth device not found!",Toast.LENGTH_SHORT).show();
        }
        else {

            mLED1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    Log.i("i am in check box","!yes");

                    if(mConnectedThread != null){ //First check to make sure thread created

                        Log.i("Data sended to blutooth","A");

                    mConnectedThread.write("A");}

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Enable Bluetooth!Pair HC-05!Connect to HC-05.", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }
            });


            left.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    Log.i("i am in left","!yes");

                    if(mConnectedThread != null){ //First check to make sure thread created

                        Log.i("Data sended to blutooth","B");


                       mConnectedThread.write("B");}

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Enable Bluetooth!Pair HC-05!Connect to HC-05.", Toast.LENGTH_LONG);
                        toast.show();

                    }


                }
            });

            mode.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    Log.i("i am in Mode","!yes");

                    if(mConnectedThread != null){ //First check to make sure thread created

                        Log.i("Data sended to blutooth","C");

                    mConnectedThread.write("C");}

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Enable Bluetooth!Pair HC-05!Connect to HC-05.", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }
            });

            reset.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    Log.i("i am in Reset","!yes");

                    if(mConnectedThread != null){ //First check to make sure thread created

                        Log.i("Data sended to blutooth","D");

                    mConnectedThread.write("D");
                        sine.setText(" ");
                        unit.setText(" ");
                        mReadBuffer.setText("00000000");
                    }

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Enable Bluetooth!Pair HC-05!Connect to HC-05.", Toast.LENGTH_LONG);
                        toast.show();



                    }
                }
            });


            mScanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetoothOn(v);
                }
            });

            mOffBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    bluetoothOff(v);
                }
            });

            mListPairedDevicesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    listPairedDevices(v);
                }
            });

            mDiscoverBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    discover(v);
                }
            });
        }
    }

    private void bluetoothOn(View view){
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            //mBluetoothStatus.setText("Bluetooth enabled");

            Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_SHORT).show();


            //Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivity(enableBtIntent);

            /*Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
            // If there are paired devices
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {


                    Log.e("Mac Addressess", "are:  " + mBTAdapter.getRemoteDevice(device.getAddress()));
                    //BluetoothDevice device = mBTAdapter.getRemoteDevice(address);
                    String macAddress = android.provider.Settings.Secure.getString(mContext.getContentResolver(), "bluetooth_address");


                }
            }*/


        }
        else{
            Toast.makeText(getApplicationContext(),"Bluetooth is already on", Toast.LENGTH_SHORT).show();
        }
    }

    // Enter here after user selects "yes" or "no" to enabling radio
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data){
        // Check which request we're responding to
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Toast toast = Toast.makeText(getApplicationContext(), "Bluetooth Enable", Toast.LENGTH_LONG);
                toast.show();
                //mBluetoothStatus.setText("Enabled");
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Bluetooht disable", Toast.LENGTH_LONG);
                toast.show();
            }
                //mBluetoothStatus.setText("Disabled");
        }
    }

    private void bluetoothOff(View view){
        mBTAdapter.disable(); // turn off

        Toast toast = Toast.makeText(getApplicationContext(), "Bluetooth disabled", Toast.LENGTH_LONG);
        toast.show();
        //mBluetoothStatus.setText("Bluetooth disabled");
        Toast.makeText(getApplicationContext(),"Bluetooth turned Off", Toast.LENGTH_SHORT).show();
    }

    private void discover(View view){
        // Check if the device is already discovering
        if(mBTAdapter.isDiscovering()){
            mBTAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(),"Discovery stopped",Toast.LENGTH_SHORT).show();
        }
        else{
            if(mBTAdapter.isEnabled()) {
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(), "Discovery started", Toast.LENGTH_SHORT).show();
                registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            }
            else{
                Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name to the list
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mBTArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    private void listPairedDevices(View view){
        mPairedDevices = mBTAdapter.getBondedDevices();
        if(mBTAdapter.isEnabled()) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), "Show Paired Devices", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            if(!mBTAdapter.isEnabled()) {
                Toast.makeText(getBaseContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast toast = Toast.makeText(getApplicationContext(), "Connecting....", Toast.LENGTH_LONG);
            toast.show();

            //mBluetoothStatus.setText("Connecting...");
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            final String address = info.substring(info.length() - 17);
            final String name = info.substring(0,info.length() - 17);

            // Spawn a new thread to avoid blocking the GUI one
            new Thread()
            {
                public void run() {
                    boolean fail = false;

                    BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                    try {
                        mBTSocket = createBluetoothSocket(device);
                    } catch (IOException e) {
                        fail = true;
                        Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                    }
                    // Establish the Bluetooth socket connection.
                    try {
                        mBTSocket.connect();
                    } catch (IOException e) {
                        try {
                            fail = true;
                            mBTSocket.close();
                            mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                    .sendToTarget();
                        } catch (IOException e2) {
                            //insert code to deal with this
                            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(fail == false) {
                        mConnectedThread = new ConnectedThread(mBTSocket);
                        mConnectedThread.start();

                        mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                .sendToTarget();
                    }
                }
            }.start();
        }
    };

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connection with BT device using UUID
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
             // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    byte[] buffer = new byte[20];  // buffer store for the stream
                    int bytes;
                    // Read from the InputStream
                    bytes = mmInStream.available();

                    if(bytes != 0) {
                        SystemClock.sleep(1); //pause and wait for rest of data. Adjust this depending on your sending speed.
                        bytes = mmInStream.available(); // how many bytes are ready to be read?
                        //Log.i("run: ", "" + buffer);

                        bytes = mmInStream.read(buffer, 0, bytes); // record how many bytes we actually read
                        mHandler.obtainMessage(MESSAGE_READ, bytes, 1, buffer)
                                .sendToTarget(); // Send the obtained bytes to the UI activity


                    }
                }
                catch (IOException e) {
                    e.printStackTrace();

                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String input) {
            byte[] bytes = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
}
