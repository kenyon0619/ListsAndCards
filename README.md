# ListsAndCards
ListsAndCards testing project

因為Github好像沒辦法不裝git直接用網頁版傳整個project的folder
所以我這邊就先放我改的4個.java檔案

我對原版的project做的改動如下

1.Person.java
因為圖片打算從手機裡面的folder讀取 而非放在drawable裡面
將int photoID全部改成Bitmap bitmap

2.RVAdapter
將onBindViewHolder中的
personViewHolder.personPhoto.setImageURI(persons.get(i).photoID);
改為下面這行
personViewHolder.personPhoto.setImageBitmap(persons.get(i).bitmap);

3.RecyclerViewActivity
  (1)加入Thread及Handler
    private Handler mUI_Handler = new Handler();
    private Handler mThreadHandler;
    private HandlerThread mThread;
    
    mThread = new HandlerThread("name");
    mThread.start();
    mThreadHandler=new Handler(mThread.getLooper());
  (2)將Person讀取圖片的路徑改成從手機的"Download"資料夾讀取 圖片檔名有  1.jpg、2.jpg、3.jpg、4.jpg(如有需要可提供圖片)
    詳見initializeData()
  (3)在OnCreate()中 initializeData();及initializeAdapter();這兩行後面 使用mUI_Handler.postDelayed 來增加新的item 延遲設定為10000毫秒
  
我遇到的問題如下：
若只使用post的話 可以順利執行，app執行就會把完整的view(4個item)呈現出來
而使用postDelayed的情況下 過了10秒後 雖然Person的List有增加(使用adapter.getItemCount()檢查) 但是UI上不會更新加上去的item UI上只呈現initializeData()中的3個item
不知道問題出在哪邊
(我認為有可能是notifyItemInserted的部分有問題 但不知道問題出在哪邊 以及解決的方法)
謝謝!
