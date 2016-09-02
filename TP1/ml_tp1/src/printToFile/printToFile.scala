////Function to preint to file easily
//def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
//    val p = new java.io.PrintWriter(f)
//    try { op(p) } finally { p.close() }
//  }
//printToFile(new File("/home/user14/Documents/MachineLearning/TP1/ml_tp1/tmp_out.csv")) { p =>
//    csv_clean.drop(1).foreach(p.println)
//}
