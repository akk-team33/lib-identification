package de.team33.libs.identification.v1;

import java.util.function.Supplier;

public class KeySupplier<R> extends Unique implements Supplier<R>
{

  private final Supplier<R> inner;

  public KeySupplier(final Supplier<R> inner)
  {
    this.inner = inner;
  }

  @Override
  public final R get()
  {
    return inner.get();
  }
}
