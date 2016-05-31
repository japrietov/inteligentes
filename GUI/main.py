import wx

pertenencia = 0


class ExamplePanel(wx.Panel):
    def __init__(self, parent):
        wx.Panel.__init__(self, parent)

        # the edit control - one line version.
        self.lblname = wx.StaticText(self, label="Tweet", pos=(20, 60))
        self.textBox = wx.TextCtrl(self, value="", pos=(70, 55), size=(700, -1))
        self.button = wx.Button(self, label="Analizar", pos=(20, 100))
        self.Bind(wx.EVT_BUTTON, self.OnClick, self.button)

        font = wx.Font(100, wx.DECORATIVE, wx.BOLD, wx.NORMAL)
        self.resultLabel = wx.StaticText(self, label="", pos=(300, 200))
        self.resultLabel.SetFont(font)

    def OnClick(self, event):
        self.analizar()
        print 'EvtText: %s\n' % event.GetString()

    def analizar(self):
        textBoxValue = self.textBox.GetValue()

        resultado = "positiv3o"
        self.resultLabel.SetLabel("67%")

        if resultado == "positivo":
            self.SetBackgroundColour((0, 255, 0))
        else:
            self.SetBackgroundColour((255, 0, 0))


class MainWindow(wx.Frame):
    def __init__(self, parent, title):
        wx.Frame.__init__(self, parent, title=title, size=(800, 600))
        panel = ExamplePanel(self)
        self.Show()


app = wx.App(False)
frame = MainWindow(None, "Sample editor")
app.MainLoop()
