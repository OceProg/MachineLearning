import scala.io.Source
import scala.math._
import java.io._
import smile._
import smile.data._
//import smile.shell._
import smile.classification._
import javax.swing.JFrame
import javax.swing.JPanel
import smile.plot.BarPlot
import smile.plot.Palette
import scala.io.Source
import smile.plot.ScatterPlot
import smile.regression.ols
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import smile.plot.Line;
import smile.plot.LinePlot;
import smile.plot.PlotCanvas;
import smile.validation.RMSE
import java.awt.Dimension
import java.awt.Robot
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import smile.math.distance.Distance
import smile.math.kernel.PolynomialKernel
import smile.math.kernel.GaussianKernel
import smile.math.kernel.LinearKernel

object tp_kmeans_sn {
  def main(args: Array[String]): Unit = {
    val path = "/home/user14/Documents/MachineLearning/DataTP/"
    val file_name1 = "Data1.mat"
    val ordi = scala.io.Source.fromFile(path + file_name1).getLines.filter(!_.isEmpty()).map(_.split("\\t"))
    var data = ordi.map { x => Array(x(0).toDouble, x(1).toDouble) }.toArray
    val x = data.map { x => Array(x(0).toDouble) }.toArray
    val y = data.map { x => x(1).toDouble }.toArray

    val xmax = sqrt(max(x.flatten.max*x.flatten.max, x.flatten.min*x.flatten.min))
    val ymax = sqrt(max(y.max*y.max, y.min*y.min))
    val pres = 0.01
    val xrange = (-xmax to xmax by pres).toArray
    val yrange = (-ymax to ymax by pres).toArray
    //val plan = (xrange zip yrange).map { p => Array(p._1, p._2) }.toArray//s.flatten
    val plan = xrange.map { x => yrange.map { y => Array(x, y) } }.flatten
    //plan.foreach(println)

    //val plot1 = smile.plot.plot(plan,'S')

    //var i = (1 to 500).toIterator
    //data.foreach { x => println(i.next()+": "+x(0)+" "+x(1)) }

    val framen = new JFrame("");
    framen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framen.setLocationRelativeTo(null);
    framen.setVisible(true);
    val lines = 2
    val col = 1
    framen.setLayout(new GridLayout(lines, col))
    framen.setSize(new Dimension(1900, 1000));

    val k = Array(2, 3, 5, 10, 15, 20)
    val nbIterations = 200
    val nbRun = 20

    for (j <- 5 to 5) {
      val clusters = smile.clustering.kmeans(data, k(j), nbIterations, nbRun)
      val cl = clusters.getClusterLabel
      println("\n KMeans for " + k(j) + "clusters")
      val cl_iter = clusters.getClusterLabel.toIterator
      val xylabels = data.map { xy => Array(xy(0), xy(1), cl_iter.next()) } 
      //xylabels.foreach(x => println(x(0)+" "+x(1)+" "+x(2)))
      val x2 = xylabels.map { x => x(0).toDouble }.toArray
      val y2 = xylabels.map { x => x(1).toDouble }.toArray
      val l2 = xylabels.map { x => x(2) }.toArray
      //val svm = smile.classification.svm(data, cl, new GaussianKernel(0.1), 10, strategy= SVM.Multiclass.ONE_VS_ONE, epoch=20)
      val svm = smile.classification.svm(data, cl, new LinearKernel, 10, strategy= SVM.Multiclass.ONE_VS_ONE, epoch=20)
      val svm_points = xrange.map { x => yrange.map { y => svm.predict(Array(x, y)) } }.flatten
     
      //plan.map { xy => svm.predict((xy(0), xy(1))) }
      //val plotscatt = new smile.plot.plot(x)
      val plot2 = smile.plot.ScatterPlot.plot(plan, svm_points, '#', Palette.COLORS)
      plot2.points(data, '#')
      plot2.setVisible(true)
      plot2.setTitle("SVM with linear kernel for " + k(j) + "clusters")
      framen.add(plot2)
      framen.getContentPane().add(plot2)
      
      //clusters.centroids().foreach(x => println(x.toList))
      //println(clusters.centroids().toList)
      //val eucD = new smile.math.distance.EuclideanDistance()
      //val dist = eucD.d(clusters.centroids()(0), clusters.centroids()(1))
      //println(frontier)
      //println(clusters)
      //val pres = 0.01
      //val frontier = plan.filter { x => eucD.d(x, clusters.centroids()(0)) <= dist/2.0+pres && eucD.d(x, clusters.centroids()(0)) >= dist/2.0-pres  && eucD.d(x, clusters.centroids()(1)) <= dist/2.0+pres && eucD.d(x, clusters.centroids()(1)) >= dist/2.0-pres}
      //val plot2 = smile.plot.line(frontier,Line.Style.SOLID, Color.GREEN)
      //plot2.canvas.setAxisLabels("Xi", "Yu")
      //plot2.setSize(new Dimension(1000,1000))      

      val canvas = smile.plot.ScatterPlot.plot(data, cl, '#', Palette.COLORS)
      //canvas.line(frontier, Line.Style.SOLID, Color.GREEN)
      //canvas.points(plan,'.')
      
      //canvas.getContentPane().add(plot2.canvas)
      canvas.setTitle("KMeans for " + k(j) + "clusters");
      canvas.setAxisLabels("X", "Y");
      canvas.setSize(new Dimension(1000, 1000));
      canvas.setVisible(true)
      
      framen.add(canvas);
      framen.getContentPane().add(canvas);
    }
    
    
    
//    val image1 = new BufferedImage(1900, 1000, BufferedImage.TYPE_INT_RGB);
//    val graphics2D = image1.createGraphics();
//    framen.paint(graphics2D);
//    val outfile = "235101520KmeansSVM"
//    ImageIO.write(image1,"jpeg", new File(path+outfile));
//    println("boop")

    //val imagebot = new java.awt.Robot().createScreenCapture(framen)

  }

  /* ... new cell ... */

  /* ... new cell ... */

  /* ... new cell ... */
}
                  