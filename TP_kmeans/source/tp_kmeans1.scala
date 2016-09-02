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

object tp_kmeans1 {

  
  val path = "/home/user14/Documents/MachineLearning/DataTP/"
  val file_name1 = "Data1.mat"
  val ordi = scala.io.Source.fromFile(path + file_name1).getLines.filter(!_.isEmpty()).map(_.split("\\t"))
  var data = ordi.map { x => Array(x(0).toDouble, x(1).toDouble) }.toArray
  val x = data.map { x => Array(x(0).toDouble) }.toArray
  val y = data.map { x => x(1).toDouble }.toArray
  var i = (1 to 500).toIterator
  //data.foreach { x => println(i.next()+": "+x(0)+" "+x(1)) }
  
  //val clusters = smile.clustering.kmeans(data, 3, runs = 20)
  //val ord = clusters.getClusterLabel
  //smile.plot.plot(data, ord, '#', Palette.COLORS)
  
  //val window = smile.plot.plot(data, 'S')
  
   val framen = new JFrame("");
    framen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framen.setLocationRelativeTo(null);
    framen.setVisible(true);
    framen.setLayout(new GridLayout(2, 3))
    framen.setSize(new Dimension(1000,1000));
  
  val k = Array(2, 3, 5, 10, 15, 20)
  val nbIterations = 200
  val nbRun = 20
  
  for (j<- 0 to 6){
    val clusters = smile.clustering.kmeans(data, k(j), nbIterations, nbRun)
    val cl = clusters.getClusterLabel
    println("\n KMeans for " + k(j) + "clusters")
    println(clusers)
    val canvas =  smile.plot.ScatterPlot.plot(data, cl, '#', Palette.COLORS)
        canvas.setTitle("KMeans for " + k(j) + "clusters");
        canvas.setAxisLabels("X", "Y");
        canvas.setSize(new Dimension(1000,1000));
        canvas.setVisible(true)
    framen.add(canvas);
    framen.getContentPane().add(canvas);    
  }
  
  //val image1 = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
  //val graphics2D = image1.createGraphics();
  //framen.paint(graphics2D);
  //val outfile = "235101520clusters_3by2"
  //ImageIO.write(image1,"jpeg", new File(path+outfile));
  //println("boop")
  
  //val imagebot = new java.awt.Robot().createScreenCapture(framen)

  /* ... new cell ... */

  

  /* ... new cell ... */

  

  /* ... new cell ... */
}
                  