package panos.awt;

import java.awt.*;

public class WizardLayout
    implements LayoutManager
{

    public WizardLayout()
    {
        this(0);
    }

    public WizardLayout(int i)
    {
        vgap = i;
    }

    public void addLayoutComponent(String s, Component component)
    {
    }

    public void removeLayoutComponent(Component component)
    {
    }

    public Dimension minimumLayoutSize(Container container)
    {
        Dimension dimension = new Dimension();
        int i = container.countComponents();
        for(int j = 0; j < i; j++)
        {
            Component component = container.getComponent(j);
            if(component.isVisible())
            {
                Dimension dimension1 = component.minimumSize();
                dimension.width = Math.max(dimension1.width, dimension.width);
                dimension.height += dimension1.height + vgap;
            }
        }

        Insets insets = container.insets();
        dimension.width += insets.left + insets.right;
        dimension.height += insets.top + insets.bottom;
        return dimension;
    }

    public Dimension preferredLayoutSize(Container container)
    {
        Dimension dimension = new Dimension();
        int i = container.countComponents();
        for(int j = 0; j < i; j++)
        {
            Component component = container.getComponent(j);
            Dimension dimension1 = component.preferredSize();
            dimension.width = Math.max(dimension1.width, dimension.width);
            dimension.height += dimension1.height + vgap;
        }

        Insets insets = container.insets();
        dimension.width += insets.left + insets.right;
        dimension.height += insets.top + insets.bottom;
        return dimension;
    }

    public void layoutContainer(Container container)
    {
        Insets insets = container.insets();
        int i = insets.top;
        container.size();
        int j = insets.left;
        int k = container.size().width - insets.right;
        int l = container.countComponents();
        for(int i1 = 0; i1 < l; i1++)
        {
            Component component = container.getComponent(i1);
            if(component.isVisible())
            {
                int j1 = component.size().height;
                component.resize(k - j, j1);
                Dimension dimension = component.preferredSize();
                component.reshape(j, i, k - j, dimension.height);
                i += dimension.height + vgap;
            }
        }

    }

    public String toString()
    {
        return getClass().getName() + "[vgap=" + vgap + "]";
    }

    int vgap;
}
