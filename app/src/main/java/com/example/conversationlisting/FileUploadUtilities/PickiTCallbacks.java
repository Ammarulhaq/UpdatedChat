package com.example.conversationlisting.FileUploadUtilities;

public interface PickiTCallbacks {
    void PickiTonUriReturned();
    void PickiTonStartListener();
    void PickiTonProgressUpdate(int progress);
    void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason, String extension);
}
