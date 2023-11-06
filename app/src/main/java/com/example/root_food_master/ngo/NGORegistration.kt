package com.example.root_food_master.ngo

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.root_food_master.NGODataModel
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.example.root_food_master.R
import com.example.root_food_master.databinding.ActivityNgoregistrationBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class NGORegistration : AppCompatActivity() {

    private lateinit var binding : ActivityNgoregistrationBinding
    private lateinit var database : FirebaseDatabase
    private var fileUrl = ""

    companion object {
        const val PICK_FILE_REQUEST_CODE = 123
    }

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null && data.data != null) {
                    val selectedImageUri: Uri = data.data!!
                    // Call the function to upload the selected image to Firebase Storage
                    uploadImageToFirebase(selectedImageUri)
                }
            }
        }

    private fun uploadImageToFirebase(uri: Uri) {
        // Get a reference to the Firebase Storage
        val storage = FirebaseStorage.getInstance()
        val storageRef: StorageReference = storage.reference

        // Create a reference to the file to be uploaded
        val imagesRef: StorageReference = storageRef.child("images/${System.currentTimeMillis()}.jpg")

        // Upload the file to Firebase Storage
        imagesRef.putFile(uri)
            .addOnSuccessListener {
                // Image uploaded successfully, get download URL
                imagesRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    // Handle the download URL (e.g., store it in a database)
                    val downloadUrl = downloadUri.toString()
                    fileUrl = downloadUrl
                    Toast.makeText(this, "Image uploaded successfully: $downloadUrl", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Handle unsuccessful uploads
                Toast.makeText(this, "Failed to upload image: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoregistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("pass")

        binding.btnUploadDocs.setOnClickListener {

            pickImage.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
        }

        binding.btnSubmit.setOnClickListener {
            val orgName = binding.etOrganisationName.text.toString()
            val mission_obj = binding.etMissionObj.text.toString()
            val legal_stru = binding.etLegalStructure.text.toString()
            val board_trustees = binding.etBoardOfDir.text.toString()
            val address = binding.etAddress.text.toString()
//            val document_url = binding.

            if (orgName.isNotEmpty()&&
                mission_obj.isNotEmpty()&&
                legal_stru.isNotEmpty()&&
                board_trustees.isNotEmpty()&&
                address.isNotEmpty()&&
                fileUrl.isNotEmpty()
                ) {
                val NGOData = NGODataModel(email!!, password!!, orgName, mission_obj, legal_stru, board_trustees, address, fileUrl)
                database.getReference("USERS").child(email).setValue(NGOData)
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }


        }

    }

//
//    private fun openFilePicker() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "*/*" // Accept all file types
//        startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            // Get the selected file's URI
//            val selectedFileUri: Uri? = data?.data
//            if (selectedFileUri != null) {
//                imageUrl = selectedFileUri.toString()
//                if (imageUrl.isNotEmpty()) {
//                    uploadFileToFirebaseStorage(uri = imageUrl.toUri())
//                }
//                // Now you can use the selectedFileUri to upload the file to Firebase Storage
//                // Call uploadFileToFirebaseStorage(selectedFileUri) here
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PICK_FILE_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, open file picker
//                openFilePicker()
//            } else {
//                openFilePicker()
//                Toast.makeText(this, "deniec", Toast.LENGTH_SHORT).show()
//                // Permission denied
//                // Handle the case where the user denied the permission
//            }
//        }
//    }
//
//    // Function to upload file to Firebase Storage
//    fun uploadFileToFirebaseStorage(uri: Uri) : String {
//        // Get a unique name for the file
//        val fileName = UUID.randomUUID().toString()
//
//        // Get reference to Firebase Storage
//        val storage = FirebaseStorage.getInstance()
//        val storageRef: StorageReference = storage.reference.child("uploads/$fileName")
//
//        // Upload file to Firebase Storage
//        val uploadTask = storageRef.putFile(uri)
//
//        // Listen for the success or failure of the upload task
//        uploadTask.addOnSuccessListener {
//            var downloadUrl = ""
//            // File uploaded successfully, get download URL
//            storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
//                // Use the download URL as needed, e.g., store it in Realtime Database
//                downloadUrl = downloadUri.toString()
//                downloadUrl = downloadUrl
//                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//                // Call a function to store the download URL in Realtime Database
//            }
//        }.addOnFailureListener {
//            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//            // Handle unsuccessful uploads
//        }
//
//        return downloadUrl
//    }
}