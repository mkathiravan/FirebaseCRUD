# Firebase Realtime Database

Store and sync data with our NoSQL cloud database. Data is synced across all clients in realtime, and remains available when your app goes offline.


The Firebase Realtime Database is a cloud-hosted database. Data is stored as JSON and synchronized in realtime to every connected client. When you build cross-platform apps with our iOS, Android, and JavaScript SDKs, all of your clients share one Realtime Database instance and automatically receive updates with the newest data.

Before understanding the next steps, we will talk about the Firebase Database Rules.

The Real-time database provides a declarative rules language. It defines how our data should be structured, how it should be indexed, and when our data can be read from and written to. By default, read and write access to our database is restricted, so only authenticated users can read or write data.

To get started without setting up Authentication, we can configure our rules for public access. These rules make our database open to anyone, even people not using our app, read and write access to our database.

{  
  "rules": {  
    ".read": true,  
    ".write": true  
  }  
}  


If we want to allow authenticated users for accessing read and write to our database, then we will use the following rules:

{  
  "rules": {  
    ".read": "auth!=null",  
    ".write": "auth!=null"  
  }  
}   

## Read Database like as below

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Notes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    ListModel listdata=dataSnapshot1.getValue(ListModel.class);
                    list.add(listdata);
                }

                notesAdapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
 ## Insert Data into Database like as below
 
     private void insertNotes(String titlesend, String descsend) {
        String id= mDatabase.push().getKey();
        ListModel listModel = new ListModel(id,titlesend,descsend);
        mDatabase.child("Notes").child(id).setValue(listModel).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Notes Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                    }
                });

    }
    
    
    
  ## Delete a data from the database
    
        private void deleteNote(String id) {
        mDatabase.child("Notes").child(id).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditActivity.this,"Note Updated",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));

                    }
                });
    }
    
![image](https://user-images.githubusercontent.com/39657409/79154590-6d23b800-7ded-11ea-8e25-e2c1cd80003d.png)
![image](https://user-images.githubusercontent.com/39657409/79154745-bd027f00-7ded-11ea-8f3f-ed787f7546c5.png)
![image](https://user-images.githubusercontent.com/39657409/79154769-c5f35080-7ded-11ea-800a-b1f845750a50.png)
